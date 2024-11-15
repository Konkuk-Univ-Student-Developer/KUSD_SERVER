package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AllFieldGetResponses {

    List<AllFieldGetResponse> allFieldGetResponses;

    public AllFieldGetResponses() {
        this.allFieldGetResponses = new ArrayList<>();
    }

    public void addAllFieldGetResponse(Field_V1 fieldV1) {
        this.allFieldGetResponses.add(AllFieldGetResponse.createMiddleResponse(fieldV1));
        this.allFieldGetResponses.add(AllFieldGetResponse.createSmallResponse(fieldV1));
        this.allFieldGetResponses.add(AllFieldGetResponse.createDetailResponse(fieldV1));
    }
}
