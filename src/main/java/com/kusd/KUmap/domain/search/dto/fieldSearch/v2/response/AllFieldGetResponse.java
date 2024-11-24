package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import com.kusd.KUmap.domain.search.domain.Field_V2;
import io.swagger.v3.oas.annotations.media.Schema;

public record AllFieldGetResponse(
        String middleField,
        String middelFieldCode,
        String smallField,
        String smallFieldCode,
        String detailField,
        @Schema(description = "상세 분야 코드는 상세 분야가 없을 경우 빈 문자열입니다.")
        String detailFieldCode
) {
    public static AllFieldGetResponse createMiddleResponse(Field_V2 middleField) {
        return new AllFieldGetResponse(
                middleField.getName(),
                middleField.getFieldCode(),
                "",
                "",
                "",
                ""
        );
    }

    public static AllFieldGetResponse createSmallResponse(Field_V2 middleField, Field_V2 smallField) {
        return new AllFieldGetResponse(
                middleField.getName(),
                middleField.getFieldCode(),
                smallField.getName(),
                smallField.getFieldCode(),
                "",
                ""
        );
    }

    public static AllFieldGetResponse createDetailResponse(Field_V2 middleField, Field_V2 smallField, Field_V2 detailField) {
        return new AllFieldGetResponse(
                middleField.getName(),
                middleField.getFieldCode(),
                smallField.getName(),
                smallField.getFieldCode(),
                detailField.getName(),
                detailField.getFieldCode()
        );
    }


}
