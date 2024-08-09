package com.kusd.KUmap.domain.field.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.service.CompetencyService;
import com.kusd.KUmap.domain.course.entity.AddInformation;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import com.kusd.KUmap.domain.field.dto.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.MiddleFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SubjectResponse;
import com.kusd.KUmap.domain.field.entity.Field;
import com.kusd.KUmap.domain.field.repository.FieldRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private final CompetencyService competencyService;
    private final CourseDetailsRepository courseDetailsRepository;


    public List<LargeFieldGetResponse> getLargeFieldList() {
        return fieldRepository.findAllByFieldCode().stream()
            .map(LargeFieldGetResponse::from)
            .toList();
    }

    public List<MiddleFieldGetResponse> getMiddleFieldList(MiddleFieldGetRequest request) {
        return fieldRepository.findAllByLargeField(request.largeField()).stream()
            .map(MiddleFieldGetResponse::from)
            .toList();
    }

    public List<SmallFieldGetResponse> getSmallFieldList(SmallFieldGetRequest request) {
        return fieldRepository.findAllByLargeFieldAndMiddleField(request.largeField(),
                request.middleField()).stream()
            .map(SmallFieldGetResponse::from)
            .toList();
    }

    public List<DetailFieldGetResponse> getDetailFieldList(DetailFieldGetRequest request) {
        return fieldRepository.findAllByLargeFieldAndMiddleFieldAndSmallField(
                request.largeField(), request.middleField(), request.smallField()).stream()
            .map(DetailFieldGetResponse::from)
            .toList();
    }

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

    public Field findFieldByFieldCode(String fieldCode) {
        return fieldRepository.findByFieldCode(fieldCode)
            .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
    }
}
