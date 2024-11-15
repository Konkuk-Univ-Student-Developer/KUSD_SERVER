package com.kusd.KUmap.domain.search.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.entity.CompetencyField;
import com.kusd.KUmap.domain.competency.repository.CompetencyFieldRepository;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import com.kusd.KUmap.domain.search.domain.Field_V1;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldValidateChecker {

    private final CompetencyFieldRepository competencyFieldRepository;
    private final CourseDetailsRepository courseDetailsRepository;

    public boolean validator(Field_V1 fieldV1) {
        List<Competency> competencies = getCompetenciesIfExistsByField(fieldV1);
        if (competencies.isEmpty()) {
            return false;
        }
        return hasCourseDetails(competencies);
    }

    private boolean hasCourseDetails(List<Competency> competencies) {
        return competencies.stream()
                .map(Competency::getCompetencyCode)
                .map(courseDetailsRepository::findAllByCompetencyCode)
                .anyMatch(courseDetails -> !courseDetails.isEmpty());
    }

    private List<Competency> getCompetenciesIfExistsByField(Field_V1 fieldV1) {
        return competencyFieldRepository.findAllByField(fieldV1).stream()
                .map(CompetencyField::getCompetency)
                .toList();
    }
}
