package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field;
import lombok.Builder;

@Builder
public record LargeFieldGetResponse(
    String fieldCode,
    String largeField
) {

    public static LargeFieldGetResponse from(Field field) {
        return LargeFieldGetResponse.builder()
            .fieldCode(field.getFieldCode())
            .largeField(field.getLargeField())
            .build();
    }
}
