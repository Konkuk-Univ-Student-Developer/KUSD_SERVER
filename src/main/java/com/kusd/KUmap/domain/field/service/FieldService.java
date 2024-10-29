package com.kusd.KUmap.domain.field.service;


import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.service.CompetencyService;
import com.kusd.KUmap.domain.course.entity.AddInformation;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import com.kusd.KUmap.domain.field.dto.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SubjectResponse;
import com.kusd.KUmap.search.entity.Field;
import com.kusd.KUmap.search.repository.FieldRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
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

    public List<String> getAllFieldList() {
        return fieldRepository.findAll().stream()
            .map(field -> {
            // 예상되는 문자열 크기에 맞춰 초기 용량 설정 (예: 64)
            StringBuilder fieldStringBuilder = new StringBuilder(field.getLargeField().length() + 64);
            fieldStringBuilder.append(field.getLargeField());

            Optional.ofNullable(field.getMiddleField())
                .ifPresent(middle -> fieldStringBuilder.append(" > ").append(middle));
            Optional.ofNullable(field.getSmallField())
                .ifPresent(small -> fieldStringBuilder.append(" > ").append(small));
            Optional.ofNullable(field.getDetailField())
                .ifPresent(detail -> fieldStringBuilder.append(" > ").append(detail));

            return fieldStringBuilder.toString();
        })
            .toList();
    }

    public List<LargeFieldGetResponse> getLargeFieldList() {
        return fieldRepository.findAllMiddleField().stream()
            .map(LargeFieldGetResponse::from)
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
