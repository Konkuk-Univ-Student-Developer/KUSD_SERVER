package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V2;
import lombok.Builder;

@Builder
public record SmallFieldGetV2Response(
    String middleField,
    String smallField,
    String smallFieldCode
) {
    public static SmallFieldGetV2Response from(String middleField, Field_V2 smallField) {
        return SmallFieldGetV2Response.builder()
            .middleField(middleField)
            .smallField(smallField.getName())
            .smallFieldCode(smallField.getFieldCode())
            .build();
    }
}
