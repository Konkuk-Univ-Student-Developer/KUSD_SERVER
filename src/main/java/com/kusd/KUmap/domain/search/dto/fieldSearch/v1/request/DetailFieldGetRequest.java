package com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Deprecated
public record DetailFieldGetRequest(
    @Schema(example = "01010100")
    String smallFieldCode,
    @Schema(example = "의회의원·고위공무원 및 기업 고위임원")
    String smallField,
    @Schema(example = "관리직(임원·부서장)")
    String middleField,
    @Schema(example = "경영·사무·금융·보험직")
    String largeField
) {

}
