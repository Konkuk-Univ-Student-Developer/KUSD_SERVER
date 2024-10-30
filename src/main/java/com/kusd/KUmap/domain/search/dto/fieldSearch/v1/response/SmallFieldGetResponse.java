package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Deprecated
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
