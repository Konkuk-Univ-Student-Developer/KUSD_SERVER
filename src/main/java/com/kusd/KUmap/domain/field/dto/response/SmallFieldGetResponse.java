package com.kusd.KUmap.domain.field.dto.response;

import com.kusd.KUmap.search.entity.Field;
import lombok.Builder;

@Builder
public record SmallFieldGetResponse(
    String fieldCode,
    String middleField,
    String smallField
) {
    public static SmallFieldGetResponse from(Field field) {
        return SmallFieldGetResponse.builder()
            .fieldCode(field.getFieldCode())
            .middleField(field.getMiddleField())
            .smallField(field.getSmallField())
            .build();
    }
}
