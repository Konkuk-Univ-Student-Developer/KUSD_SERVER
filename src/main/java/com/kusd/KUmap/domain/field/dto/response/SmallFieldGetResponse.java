package com.kusd.KUmap.domain.field.dto.response;

import com.kusd.KUmap.domain.field.entity.Field;
import lombok.Builder;

@Builder
public record SmallFieldGetResponse(
    String fieldCode,
    String largeField,
    String middleField,
    String smallField
) {
    public static SmallFieldGetResponse from(Field field) {
        return SmallFieldGetResponse.builder()
            .fieldCode(field.getFieldCode())
            .largeField(field.getLargeField())
            .middleField(field.getMiddleField())
            .smallField(field.getSmallField())
            .build();
    }
}
