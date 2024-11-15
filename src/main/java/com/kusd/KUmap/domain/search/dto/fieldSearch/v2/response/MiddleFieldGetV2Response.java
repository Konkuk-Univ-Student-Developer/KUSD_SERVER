package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import lombok.Builder;

@Builder
public record MiddleFieldGetV2Response(
    String middleField
) {
    public static MiddleFieldGetV2Response from(String middleField) {
        return MiddleFieldGetV2Response.builder()
            .middleField(middleField)
            .build();
    }
}
