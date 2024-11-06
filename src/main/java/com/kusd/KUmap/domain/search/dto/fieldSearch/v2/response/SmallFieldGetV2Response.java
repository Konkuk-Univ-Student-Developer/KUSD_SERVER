package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Builder
public record SmallFieldGetV2Response(
    String middleField,
    String smallField
) {
    public static SmallFieldGetV2Response from(String middleField,String smallField) {
        return SmallFieldGetV2Response.builder()
            .middleField(middleField)
            .smallField(smallField)
            .build();
    }
}
