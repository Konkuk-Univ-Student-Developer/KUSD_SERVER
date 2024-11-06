package com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response;

import com.kusd.KUmap.domain.search.domain.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AllFieldGetResponses {

    List<AllFieldGetResponse> allFieldGetResponses;

    public AllFieldGetResponses() {
        this.allFieldGetResponses = new ArrayList<>();
    }

    public void addAllFieldGetResponse(Field field) {
        this.allFieldGetResponses.add(AllFieldGetResponse.createMiddleResponse(field));
        this.allFieldGetResponses.add(AllFieldGetResponse.createSmallResponse(field));
        this.allFieldGetResponses.add(AllFieldGetResponse.createDetailResponse(field));
    }
}
