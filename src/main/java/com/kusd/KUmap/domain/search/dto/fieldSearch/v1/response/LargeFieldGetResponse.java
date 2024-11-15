package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import lombok.Builder;

@Deprecated
@Builder
public record LargeFieldGetResponse(
    String fieldCode,
    String largeField
) {

    public static LargeFieldGetResponse from(Field_V1 fieldV1) {
        return LargeFieldGetResponse.builder()
            .fieldCode(fieldV1.getFieldCode())
            .largeField(fieldV1.getLargeField())
            .build();
    }
}
