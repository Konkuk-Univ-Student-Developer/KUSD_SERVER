package com.kusd.KUmap.search.service;

import com.kusd.KUmap.domain.field.dto.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SmallFieldGetResponse;
import com.kusd.KUmap.search.entity.Field;
import com.kusd.KUmap.search.repository.FieldRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldSearchService {

    private final FieldRepository fieldRepository;
    private final FieldValidator competencyChecker;

    @Cacheable("fieldList")
    public List<String> getAllFieldList() {
        return fieldRepository.findAll().stream()
                .filter(competencyChecker::validator)
                .map(Field::getFieldSearchFormat)
                .filter(field -> !field.isBlank())
                .toList();
    }

    @Cacheable("MiddleFieldList")
    public List<MiddleFieldGetResponse> getMiddleFieldList() {
        return fieldRepository.findAllMiddleField().stream()
                .filter(Field::hasMiddleField)
                .filter(competencyChecker::validator)
                .map(MiddleFieldGetResponse::from)
                .toList();
    }

    @Cacheable("SmallFieldList")
    public List<SmallFieldGetResponse> getSmallFieldList(SmallFieldGetRequest request) {
        return fieldRepository.findAllByMiddleField(request.middleField()).stream()
                .filter(Field::hasSmallField)
                .filter(competencyChecker::validator)
                .map(SmallFieldGetResponse::from)
                .toList();
    }

    @Cacheable("DetailFieldList")
    public List<DetailFieldGetResponse> getDetailFieldList(DetailFieldGetRequest request) {
        return fieldRepository.findAllByMiddleFieldAndSmallField(request.middleField(), request.smallField()).stream()
                .filter(Field::hasDetailField)
                .filter(competencyChecker::validator)
                .map(DetailFieldGetResponse::from)
                .toList();
    }
}
