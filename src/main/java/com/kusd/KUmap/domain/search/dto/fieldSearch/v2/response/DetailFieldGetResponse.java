package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import com.kusd.KUmap.domain.search.domain.Field_V2;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.DetailFieldGetV2Request;
import lombok.Builder;

@Builder
public record DetailFieldGetResponse(
    String middleField,
    String smallField,
    String detailField,
    String detailFieldCode
) {
    public static DetailFieldGetResponse from(DetailFieldGetV2Request request, Field_V2 fieldV2) {
        String fieldName = fieldV2.getName();
        if (request.smallFieldCode().equals(fieldV2.getFieldCode())) {
            fieldName = "공통";
        }
        return DetailFieldGetResponse.builder()
            .middleField(request.middleField())
            .smallField(request.smallField())
            .detailField(fieldName)
            .detailFieldCode(fieldV2.getFieldCode())
            .build();
    }
}