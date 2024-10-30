package com.kusd.KUmap.domain.search.service;

import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.search.domain.Field;
import com.kusd.KUmap.domain.search.repository.FieldSearchV2Repository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldSearchV2Service {

    private final FieldSearchV2Repository fieldSearchV2Repository;
    private final FieldValidateService competencyChecker;

    @Cacheable("fieldList")
    public List<String> getAllFieldList() {
        return fieldSearchV2Repository.findAll().stream()
                .filter(competencyChecker::validator)
                .map(Field::getFieldSearchFormat)
                .filter(field -> !field.isBlank())
                .toList();
    }

    @Cacheable("MiddleFieldList")
    public List<MiddleFieldGetResponse> getMiddleFieldList() {
        return fieldSearchV2Repository.findAllMiddleField().stream()
                .filter(Field::hasMiddleField)
                .filter(competencyChecker::validator)
                .map(MiddleFieldGetResponse::from)
                .toList();
    }

    @Cacheable("SmallFieldList")
    public List<SmallFieldGetResponse> getSmallFieldList(SmallFieldGetRequest request) {
        return fieldSearchV2Repository.findAllByMiddleField(request.middleField()).stream()
                .filter(Field::hasSmallField)
                .filter(competencyChecker::validator)
                .map(SmallFieldGetResponse::from)
                .toList();
    }

    @Cacheable("DetailFieldList")
    public List<DetailFieldGetResponse> getDetailFieldList(DetailFieldGetRequest request) {
        return fieldSearchV2Repository.findAllByMiddleFieldAndSmallField(request.middleField(), request.smallField()).stream()
                .filter(Field::hasDetailField)
                .filter(competencyChecker::validator)
                .map(DetailFieldGetResponse::from)
                .toList();
    }
}
