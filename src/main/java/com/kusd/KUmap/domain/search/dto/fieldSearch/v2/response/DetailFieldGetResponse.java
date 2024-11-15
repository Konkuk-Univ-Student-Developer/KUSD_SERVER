package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import lombok.Builder;

@Builder
public record DetailFieldGetResponse(
    String detailFieldCode,
    String middleField,
    String smallField,
    String detailField
) {
    public static DetailFieldGetResponse from(Field_V1 fieldV1) {
        return DetailFieldGetResponse.builder()
            .detailFieldCode(fieldV1.getFieldCode())
            .middleField(fieldV1.getMiddleField())
            .smallField(fieldV1.getSmallField())
            .detailField(fieldV1.getDetailField())
            .build();
    }
}