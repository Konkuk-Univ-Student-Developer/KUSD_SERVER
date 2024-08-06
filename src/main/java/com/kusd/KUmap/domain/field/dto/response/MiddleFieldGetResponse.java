package com.kusd.KUmap.domain.field.dto.response;

import com.kusd.KUmap.domain.field.entity.Field;
import lombok.Builder;

@Builder
public record MiddleFieldGetResponse(
    String fieldCode,
    String largeField,
    String middleField
) {
    public static MiddleFieldGetResponse from(Field field) {
        return MiddleFieldGetResponse.builder()
            .fieldCode(field.getFieldCode())
            .largeField(field.getLargeField())
            .middleField(field.getMiddleField())
            .build();
    }
}
