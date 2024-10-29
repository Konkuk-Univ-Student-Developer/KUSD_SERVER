package com.kusd.KUmap.domain.competency.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.entity.CompetencyField;
import com.kusd.KUmap.domain.competency.repository.CompetencyFieldRepository;
import com.kusd.KUmap.domain.competency.repository.CompetencyRepository;
import com.kusd.KUmap.search.entity.Field;
import com.kusd.KUmap.search.repository.FieldRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetencyService {

    private final FieldRepository fieldRepository;

    private final CompetencyFieldRepository competencyFieldRepository;
    private final CompetencyRepository competencyRepository;

    public List<Competency> getCompetencyListByFieldCode(String fieldCode) {
        Field field = findFieldByFieldCode(fieldCode);

        List<CompetencyField> competencyFieldList = competencyFieldRepository.findAllByField(field);

        return competencyFieldList.stream()
            .map(CompetencyField::getCompetency)
            .toList();
    }

    public Competency getCompetencyByCmptCode(String cmptCode) {
        return competencyRepository.findByCompetencyCode(cmptCode);
    }

    private Field findFieldByFieldCode(String fieldCode) {
        return fieldRepository.findByFieldCode(fieldCode)
            .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
    }
}
