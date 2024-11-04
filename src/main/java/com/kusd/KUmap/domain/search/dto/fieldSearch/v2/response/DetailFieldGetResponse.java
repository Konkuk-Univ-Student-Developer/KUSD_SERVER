package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Builder
public record DetailFieldGetResponse(
    String detailFieldCode,
    String middleField,
    String smallField,
    String detailField
) {
    public static DetailFieldGetResponse from(Field field) {
        return DetailFieldGetResponse.builder()
            .detailFieldCode(field.getFieldCode())
            .middleField(field.getMiddleField())
            .smallField(field.getSmallField())
            .detailField(field.getDetailField())
            .build();
    }
}