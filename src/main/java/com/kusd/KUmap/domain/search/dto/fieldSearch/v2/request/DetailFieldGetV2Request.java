package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record DetailFieldGetV2Request(
    @Schema(example = "의회의원·고위공무원 및 기업 고위임원")
    String smallField,
    @Schema(example = "관리직(임원·부서장)")
    String middleField
) {

}
