package com.kusd.KUmap.domain.search.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.service.CompetencyService;
import com.kusd.KUmap.domain.course.entity.AddInformation;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import com.kusd.KUmap.domain.search.dto.subjectSearch.response.SubjectResponse;
import com.kusd.KUmap.domain.search.repository.FieldCommonRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
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
    private final CourseDetailsRepository courseDetailsRepository;

    public Set<SubjectResponse> getSubjectListByField(String fieldCode) {

        findFieldByFieldCode(fieldCode);

        List<Competency> competencyList = competencyService.getCompetencyListByFieldCode(fieldCode);

        List<CourseDetails> courseDetailsList = competencyList.stream()
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

    private List<CourseDetails> getCourseListByCompetencyCode(String cmptCode) {
        return courseDetailsRepository.findAllByCompetencyCode(cmptCode);
    }

    public void findFieldByFieldCode(String fieldCode) {
        fieldCommonRepository.findByFieldCode(fieldCode)
                .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
    }
}
