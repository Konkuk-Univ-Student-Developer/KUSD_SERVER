package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import lombok.Builder;

@Deprecated
@Builder
public record DetailFieldGetResponse(
    String fieldCode,
    String largeField,
    String middleField,
    String smallField,
    String detailField
) {
    public static DetailFieldGetResponse from(Field_V1 fieldV1) {
        return DetailFieldGetResponse.builder()
            .fieldCode(fieldV1.getFieldCode())
            .largeField(fieldV1.getLargeField())
            .middleField(fieldV1.getMiddleField())
            .smallField(fieldV1.getSmallField())
            .detailField(fieldV1.getDetailField())
            .build();
    }
}