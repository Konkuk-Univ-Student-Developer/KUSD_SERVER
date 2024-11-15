package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import lombok.Builder;

@Deprecated
@Builder
public record SmallFieldGetResponse(
    String fieldCode,
    String middleField,
    String smallField
) {
    public static SmallFieldGetResponse from(Field_V1 fieldV1) {
        return SmallFieldGetResponse.builder()
            .fieldCode(fieldV1.getFieldCode())
            .middleField(fieldV1.getMiddleField())
            .smallField(fieldV1.getSmallField())
            .build();
    }
}
