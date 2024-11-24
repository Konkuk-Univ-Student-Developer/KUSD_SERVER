package com.kusd.KUmap.domain.competency.service;

import com.kusd.KUmap.domain.competency.entity.Competency;
import com.kusd.KUmap.domain.competency.entity.CompetencyField;
import com.kusd.KUmap.domain.competency.repository.CompetencyFieldRepository;
import com.kusd.KUmap.domain.competency.repository.CompetencyRepository;
import com.kusd.KUmap.domain.search.domain.Field_V2;
import com.kusd.KUmap.domain.search.repository.FieldV2SearchRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetencyService {

    private final FieldV2SearchRepository fieldV2SearchRepository;

    private final CompetencyFieldRepository competencyFieldRepository;
    private final CompetencyRepository competencyRepository;

    public List<Competency> getCompetencyListByFieldCode(String fieldCode) {
        Field_V2 fieldV2 = findFieldByFieldCode(fieldCode);

        List<CompetencyField> competencyFieldList = competencyFieldRepository.findAllByField(fieldV2);

        return competencyFieldList.stream()
            .map(CompetencyField::getCompetency)
            .toList();
    }

    public Competency getCompetencyByCmptCode(String cmptCode) {
        return competencyRepository.findByCompetencyCode(cmptCode);
    }

    private Field_V2 findFieldByFieldCode(String fieldCode) {
        return fieldV2SearchRepository.findByFieldCode(fieldCode)
            .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
    }
}
