package com.kusd.KUmap.domain.field.service;

import com.kusd.KUmap.domain.field.dto.request.MiddleFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.field.repository.FieldRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;

    public List<LargeFieldGetResponse> getLargeFieldList() {
        return fieldRepository.findAllByFieldCode().stream()
            .map(LargeFieldGetResponse::from)
            .toList();
    }

    public List<MiddleFieldGetResponse> getMiddleFieldList(MiddleFieldGetRequest request) {
        return fieldRepository.findAllByLargeFieldAndMiddleFieldAndSmallField(request.largeField()).stream()
            .map(MiddleFieldGetResponse::from)
            .toList();
    }

    public List<SmallFieldGetResponse> getSmallFieldList(SmallFieldGetRequest request) {
        return fieldRepository.findAllByLargeFieldAndMiddleFieldAndSmallFieldAndDetailField(request.largeField(),
                request.middleField()).stream()
            .map(SmallFieldGetResponse::from)
            .toList();
    }

    void getDetailFieldList() {

    }

    void getSubjectListByField() {

    }
}
