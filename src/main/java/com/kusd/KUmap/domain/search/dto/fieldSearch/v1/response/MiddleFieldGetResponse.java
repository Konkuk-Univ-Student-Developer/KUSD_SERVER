package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import lombok.Builder;

@Deprecated
@Builder
public record MiddleFieldGetResponse(
    String fieldCode,
    String middleField
) {
    public static MiddleFieldGetResponse from(Field_V1 fieldV1) {
        return MiddleFieldGetResponse.builder()
            .fieldCode(fieldV1.getFieldCode())
            .middleField(fieldV1.getMiddleField())
            .build();
    }
}
