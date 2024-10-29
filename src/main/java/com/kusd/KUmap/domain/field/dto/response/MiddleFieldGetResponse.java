package com.kusd.KUmap.domain.field.dto.response;

import com.kusd.KUmap.search.entity.Field;
import lombok.Builder;

@Builder
public record MiddleFieldGetResponse(
    String fieldCode,
    String middleField
) {
    public static MiddleFieldGetResponse from(Field field) {
        return MiddleFieldGetResponse.builder()
            .fieldCode(field.getFieldCode())
            .middleField(field.getMiddleField())
            .build();
    }
}
