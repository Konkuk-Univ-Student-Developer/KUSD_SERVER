package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MiddleFieldGetRequest(
    @Schema(example = "01000000")
    String largeFieldCode,
    @Schema(example = "경영·사무·금융·보험직")
    String largeField
) {

}
