package com.kusd.KUmap.domain.field.dto.response;

import com.kusd.KUmap.domain.field.entity.Field;
import lombok.Builder;

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