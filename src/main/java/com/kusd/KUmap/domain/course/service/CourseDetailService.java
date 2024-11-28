package com.kusd.KUmap.domain.course.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.service.CompetencyService;
import com.kusd.KUmap.domain.course.dto.response.CourseCompetencyResponse;
import com.kusd.KUmap.domain.course.dto.response.CourseCompetencySubjectResponse;
import com.kusd.KUmap.domain.course.dto.response.CourseGetDetailsResponse;
import com.kusd.KUmap.domain.course.dto.response.CourseGetResponse;
import com.kusd.KUmap.domain.course.dto.response.CourseSubjectGetResponse;
import com.kusd.KUmap.domain.course.entity.CompetencyInCourse;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import com.kusd.KUmap.domain.search.domain.Field_V2;
import com.kusd.KUmap.domain.search.repository.FieldV2SearchRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseDetailService {

    private final CourseDetailsRepository courseDetailsRepository;
    private final CompetencyService competencyService;
    private final FieldV2SearchRepository fieldV2SearchRepository;

    public List<CourseDetails> getCourseListByCompetencyCode(String cmptCode) {
        return courseDetailsRepository.findAllByCompetencyCode(cmptCode);
    }

    public List<CourseGetResponse> getCourseListBySubject(String subjectCode) {
        return courseDetailsRepository.findAllByOpeningSubjectCode(subjectCode).stream()
            .map(CourseGetResponse::from)
            .toList();
    }

    public List<CourseCompetencySubjectResponse> getCourseListByField(String fieldCode) {
        // 진출분야의 전공역량
        List<Competency> competencyListByField = competencyService.getCompetencyListByFieldCode(fieldCode);

        // 중복된 키가 발생할 경우 기존 리스트에 새 리스트를 추가하여 병합
        Map<String, List<CourseDetails>> resultMap = competencyListByField.stream()
            .map(Competency::getCompetencyCode)
            .collect(Collectors.toMap(
                code -> code,
                courseDetailsRepository::findAllByCompetencyCode,
                (existing, replacement) -> {
                    existing.addAll(replacement); // 기존 리스트에 새로운 리스트를 추가
                    return existing;
                }
            ));
        resultMap.entrySet().removeIf(entry -> entry.getValue().isEmpty()); // 비어 있는 전공역량 지움

        List<CourseCompetencySubjectResponse> responseList = new ArrayList<>();
        for (String cmptCode : resultMap.keySet()) {
            Competency competency = competencyService.getCompetencyByCmptCode(cmptCode);
            CourseCompetencySubjectResponse from = CourseCompetencySubjectResponse.from(competency);
            from.addCourseSubjectGetResponseList(resultMap.get(cmptCode).stream()
                .map(CourseSubjectGetResponse::from)
                .toList());
            responseList.add(from);
        }
        return responseList;
    }

    public List<CourseCompetencyResponse> getCourseListBySubjectAndField(String subjectCode, String fieldCode) {

        // 진출분야의 전공역량
        List<Competency> competencyListByField = getCompetencies(fieldCode);

        // 학과의 전공역량
        Set<String> competencyNameListBySubject = extractCompetencyNamesFromCourses(subjectCode);

        // 공통 전공역량 필터링
        List<Competency> commonCmptList = filterCommonCompetencies(competencyListByField, competencyNameListBySubject);

        // 결과 매핑
        Map<String, List<CourseDetails>> resultMap = mapCoursesByCompetencyCode(courseDetailsRepository.findAllByOpeningSubjectCode(subjectCode), commonCmptList);

        // 응답 생성
        return createResponseList(resultMap);
    }

    private List<Competency> getCompetencies(String fieldCode) {
        if(fieldCode.substring(6,8).equals("00")){
            List<Competency> competencies = new ArrayList<>(
                    fieldV2SearchRepository.findAllByParentFieldCode(fieldCode).stream()
                            .map(Field_V2::getFieldCode)
                            .map(competencyService::getCompetencyListByFieldCode)
                            .flatMap(List::stream)
                            .toList());
            competencies.addAll(competencyService.getCompetencyListByFieldCode(fieldCode));

            return competencies;
        }
        return competencyService.getCompetencyListByFieldCode(fieldCode);
    }

    private Set<String> extractCompetencyNamesFromCourses(String subjectCode) {
        return courseDetailsRepository.findAllByOpeningSubjectCode(subjectCode).stream()
            .map(CourseDetails::getCompetencyInCourse)
            .filter(Objects::nonNull)
            .flatMap(competencyInCourse -> Stream.of(
                competencyInCourse.getCompetencyCode1(),
                competencyInCourse.getCompetencyCode2(),
                competencyInCourse.getCompetencyCode3()
            ))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    private List<Competency> filterCommonCompetencies(List<Competency> competencyListByField, Set<String> competencyNameListBySubject) {
        return competencyListByField.stream()
            .filter(competency -> competencyNameListBySubject.contains(competency.getCompetencyCode()))
            .collect(Collectors.toList());
    }

    private Map<String, List<CourseDetails>> mapCoursesByCompetencyCode(List<CourseDetails> courseDetailsList, List<Competency> commonCmptList) {
        Map<String, List<CourseDetails>> resultMap = commonCmptList.stream()
            .map(Competency::getCompetencyCode)
            .collect(Collectors.toMap(
                code -> code,
                code -> new ArrayList<>(),
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                }
            ));

        courseDetailsList.forEach(courseDetails -> {
            CompetencyInCourse competencyInCourse = courseDetails.getCompetencyInCourse();
            if (competencyInCourse != null) {
                addCourseToResultMap(resultMap, competencyInCourse, courseDetails);
            }
        });

        return resultMap;
    }

    private void addCourseToResultMap(Map<String, List<CourseDetails>> resultMap, CompetencyInCourse competencyInCourse, CourseDetails courseDetails) {
        Stream.of(
                competencyInCourse.getCompetencyCode1(),
                competencyInCourse.getCompetencyCode2(),
                competencyInCourse.getCompetencyCode3()
            ).filter(Objects::nonNull)
            .forEach(code -> {
                List<CourseDetails> courses = resultMap.get(code);
                if (courses != null) {
                    courses.add(courseDetails);
                }
            });
    }

    private List<CourseCompetencyResponse> createResponseList(Map<String, List<CourseDetails>> resultMap) {
        return resultMap.entrySet().stream()
            .map(entry -> {
                Competency competency = competencyService.getCompetencyByCmptCode(entry.getKey());
                CourseCompetencyResponse response = CourseCompetencyResponse.from(competency);
                response.addCourseGetResponseList(entry.getValue().stream()
                    .map(CourseGetResponse::from)
                    .collect(Collectors.toList()));
                return response;
            })
            .collect(Collectors.toList());
    }
    private void addIfNotNull(Set<String> set, String competencyCode) {
        if (competencyCode != null) {
            set.add(competencyCode);
        }
    }

    public CourseGetDetailsResponse getCourseDetails(String haksuId) {
        CourseDetails courseDetails = courseDetailsRepository.findByHaksuId(haksuId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.Course_NOT_FOUND));

        return CourseGetDetailsResponse.from(courseDetails);
    }
}
