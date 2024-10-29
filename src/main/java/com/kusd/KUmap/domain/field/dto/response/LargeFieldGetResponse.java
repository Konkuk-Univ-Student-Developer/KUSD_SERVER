package com.kusd.KUmap.domain.field.dto.response;

import com.kusd.KUmap.search.entity.Field;
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
