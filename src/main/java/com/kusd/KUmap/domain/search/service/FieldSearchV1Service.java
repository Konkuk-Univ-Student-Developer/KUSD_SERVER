package com.kusd.KUmap.domain.search.service;


import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request.MiddleFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.search.repository.FieldSearchV1Repository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Deprecated
public class FieldSearchV1Service {

    private final FieldSearchV1Repository fieldRepository;


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



}
