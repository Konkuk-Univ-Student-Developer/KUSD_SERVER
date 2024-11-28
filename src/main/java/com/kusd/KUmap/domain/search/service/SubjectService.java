package com.kusd.KUmap.domain.search.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.service.CompetencyService;
import com.kusd.KUmap.domain.course.entity.AddInformation;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import com.kusd.KUmap.domain.search.domain.Field_V1;
import com.kusd.KUmap.domain.search.domain.Field_V2;
import com.kusd.KUmap.domain.search.dto.subjectSearch.response.SubjectResponse;
import com.kusd.KUmap.domain.search.repository.FieldCommonRepository;
import com.kusd.KUmap.domain.search.repository.FieldV2SearchRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final CompetencyService competencyService;
    private final FieldCommonRepository fieldCommonRepository;
    private final FieldV2SearchRepository fieldV2SearchRepository;
    private final CourseDetailsRepository courseDetailsRepository;

    public Set<SubjectResponse> getSubjectListByField(String fieldCode) {
        findFieldByFieldCode(fieldCode);
        List<Competency> competencies = getCompetencies(fieldCode);

        List<CourseDetails> courseDetailsList = competencies.stream()
                .flatMap(competency -> getCourseListByCompetencyCode(competency.getCompetencyCode()).stream())
                .toList();

        return courseDetailsList.stream()
                .map(courseDetails -> {
                    AddInformation addInformation = courseDetails.getAddInformation();
                    return SubjectResponse.of(addInformation.getOpeningSubjectName(),
                            addInformation.getOpeningSubjectCode());
                })
                .collect(Collectors.toSet());
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

    private List<CourseDetails> getCourseListByCompetencyCode(String cmptCode) {
        return courseDetailsRepository.findAllByCompetencyCode(cmptCode);
    }

    public void findFieldByFieldCode(String fieldCode) {
        fieldCommonRepository.findByFieldCode(fieldCode)
                .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
    }
}
