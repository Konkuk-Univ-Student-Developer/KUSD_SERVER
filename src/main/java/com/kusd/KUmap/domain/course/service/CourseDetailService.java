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
import com.kusd.KUmap.domain.field.entity.Field;
import com.kusd.KUmap.domain.field.service.FieldService;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseDetailService {

    private final CourseDetailsRepository courseDetailsRepository;
    private final FieldService fieldService;
    private final CompetencyService competencyService;

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
        List<Competency> competencyListByField = competencyService.getCompetencyListByFieldCode(fieldCode);

        // 학과의 전공역량
        List<CourseDetails> courseDetailsList = courseDetailsRepository.findAllByOpeningSubjectCode(subjectCode);
        Set<String> competencyNameListBySubject = new HashSet<>();
        courseDetailsList.forEach(course -> {
            CompetencyInCourse competencyInCourse = course.getCompetencyInCourse();

            addIfNotNull(competencyNameListBySubject, competencyInCourse.getCompetencyCode1());
            addIfNotNull(competencyNameListBySubject, competencyInCourse.getCompetencyCode2());
            addIfNotNull(competencyNameListBySubject, competencyInCourse.getCompetencyCode3());
        });

        List<Competency> commonCmptList = competencyListByField.stream()
            .filter(competency -> competencyNameListBySubject.contains(competency.getCompetencyCode()))
            .toList();

        Map<String, List<CourseDetails>> resultMap = commonCmptList.stream()
            .map(Competency::getCompetencyCode)
            .collect(Collectors.toMap(
                code -> code,
                code -> new ArrayList<>()
            ));

        for (CourseDetails courseDetails : courseDetailsList) {
            CompetencyInCourse competencyInCourse = courseDetails.getCompetencyInCourse();
            String cmpt1 = competencyInCourse.getCompetencyCode1();
            String cmpt2 = competencyInCourse.getCompetencyCode2();
            String cmpt3 = competencyInCourse.getCompetencyCode3();

            for (Competency competency : commonCmptList) {
                String cmpt = competency.getCompetencyCode();
                if(cmpt.equals(cmpt1)) {
                    resultMap.get(cmpt).add(courseDetails);
                }
                if(cmpt.equals(cmpt2)){
                    resultMap.get(cmpt).add(courseDetails);
                }
                if(cmpt.equals(cmpt3)){
                    resultMap.get(cmpt).add(courseDetails);
                }
            }
        }

        List<CourseCompetencyResponse> responseList = new ArrayList<>();
        for (String cmptCode : resultMap.keySet()) {
            Competency competency = competencyService.getCompetencyByCmptCode(cmptCode);
            CourseCompetencyResponse from = CourseCompetencyResponse.from(competency);
            from.addCourseGetResponseList(resultMap.get(cmptCode).stream()
                .map(CourseGetResponse::from)
                .toList());
            responseList.add(from);
        }

        return responseList;
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
