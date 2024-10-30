package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Deprecated
@Builder
public record DetailFieldGetResponse(
    String fieldCode,
    String largeField,
    String middleField,
    String smallField,
    String detailField
) {
    public static DetailFieldGetResponse from(Field field) {
        return DetailFieldGetResponse.builder()
            .fieldCode(field.getFieldCode())
            .largeField(field.getLargeField())
            .middleField(field.getMiddleField())
            .smallField(field.getSmallField())
            .detailField(field.getDetailField())
            .build();
    }
}