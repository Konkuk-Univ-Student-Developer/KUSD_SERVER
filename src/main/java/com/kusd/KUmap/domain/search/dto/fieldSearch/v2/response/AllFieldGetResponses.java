package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import com.kusd.KUmap.domain.search.domain.Field_V2;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;

@Getter
public class AllFieldGetResponses {

    Set<AllFieldGetResponse> allFieldGetResponses;

    public AllFieldGetResponses() {
        this.allFieldGetResponses = new LinkedHashSet<>();
    }

    public void addAllFieldGetResponse(Field_V2 middleField, Field_V2 smallField, Field_V2 detailField) {
        this.allFieldGetResponses.add(AllFieldGetResponse.createMiddleResponse(middleField));
        this.allFieldGetResponses.add(AllFieldGetResponse.createSmallResponse(middleField ,smallField));
        this.allFieldGetResponses.add(AllFieldGetResponse.createDetailResponse(middleField ,smallField, detailField));
    }
}
