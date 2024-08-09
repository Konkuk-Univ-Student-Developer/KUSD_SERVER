package com.kusd.KUmap.domain.field.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.entity.CompetencyField;
import com.kusd.KUmap.domain.competency.repository.CompetencyFieldRepository;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.service.CourseDetailService;
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
    private final CompetencyFieldRepository competencyFieldRepository;
    private final CourseDetailService courseDetailService;

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

        Field field = fieldRepository.findByFieldCode(fieldCode)
            .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));

        List<CompetencyField> competencyFieldList = competencyFieldRepository.findAllByField(field);

        List<Competency> competencyList = competencyFieldList.stream()
            .map(CompetencyField::getCompetency)
            .toList();

        List<CourseDetails> courseDetailsList = competencyList.stream()
            .flatMap(competency -> courseDetailService.getCourseListByCompetencyCode(competency.getCompetencyCode()).stream())
            .toList();

        return courseDetailsList.stream()
            .map(courseDetails -> SubjectResponse.of(courseDetails.getOpeningSubjectName(), courseDetails.getOpeningSubjectCode()))
            .collect(Collectors.toSet());
    }
}
