package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SmallFieldGetRequest(
    @Schema(example = "01010100")
    String middleFieldCode,
    @Schema(example = "관리직(임원·부서장)")
    String middleField,
    @Schema(example = "경영·사무·금융·보험직")
    String largeField
) {

}
