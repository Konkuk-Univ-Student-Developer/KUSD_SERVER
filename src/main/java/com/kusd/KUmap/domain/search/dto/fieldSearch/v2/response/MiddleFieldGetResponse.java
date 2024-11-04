package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Builder
public record MiddleFieldGetResponse(
    String middleFieldCode,
    String middleField
) {
    public static MiddleFieldGetResponse from(Field field) {
        return MiddleFieldGetResponse.builder()
            .middleFieldCode(field.getFieldCode())
            .middleField(field.getMiddleField())
            .build();
    }
}
