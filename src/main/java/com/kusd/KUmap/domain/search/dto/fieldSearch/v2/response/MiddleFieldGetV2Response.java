package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V2;
import lombok.Builder;

@Builder
public record MiddleFieldGetV2Response(
    String middleField,
    String middleFieldCode
) {
    public static MiddleFieldGetV2Response from(Field_V2 middleField) {
        return MiddleFieldGetV2Response.builder()
            .middleField(middleField.getName())
            .middleFieldCode(middleField.getFieldCode())
            .build();
    }
}
