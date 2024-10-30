package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Deprecated
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
