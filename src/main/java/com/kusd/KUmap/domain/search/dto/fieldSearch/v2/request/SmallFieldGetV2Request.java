package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SmallFieldGetV2Request(
    @Schema(example = "관리직(임원·부서장)")
    String middleField
) {

}
