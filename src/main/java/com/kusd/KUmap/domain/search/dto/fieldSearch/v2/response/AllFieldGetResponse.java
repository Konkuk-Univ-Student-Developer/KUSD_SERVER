package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import io.swagger.v3.oas.annotations.media.Schema;

public record AllFieldGetResponse(
        String middleField,
        String smallField,
        String detailField,
        @Schema(description = "상세 분야 코드는 상세 분야가 없을 경우 빈 문자열입니다.")
        String detailFieldCode
) {
    public static AllFieldGetResponse createMiddleResponse(Field_V1 fieldV1) {
        return new AllFieldGetResponse(
                fieldV1.getMiddleField(),
                "",
                "",
                ""
        );
    }

    public static AllFieldGetResponse createSmallResponse(Field_V1 fieldV1) {
        return new AllFieldGetResponse(
                fieldV1.getMiddleField(),
                fieldV1.getSmallField(),
                "",
                ""
        );
    }

    public static AllFieldGetResponse createDetailResponse(Field_V1 fieldV1) {
        return new AllFieldGetResponse(
                fieldV1.getMiddleField(),
                fieldV1.getSmallField(),
                fieldV1.getDetailField(),
                fieldV1.getFieldCode()
        );
    }
}
