package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Builder
public record SmallFieldGetResponse(
    String smallFieldCode,
    String middleField,
    String smallField
) {
    public static SmallFieldGetResponse from(Field field) {
        return SmallFieldGetResponse.builder()
            .smallFieldCode(field.getFieldCode())
            .middleField(field.getMiddleField())
            .smallField(field.getSmallField())
            .build();
    }
}
