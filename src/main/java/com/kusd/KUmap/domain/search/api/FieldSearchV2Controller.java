package com.kusd.KUmap.domain.search.api;

import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.DetailFieldGetV2Request;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.SmallFieldGetV2Request;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.AllFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.MiddleFieldGetV2Response;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.SmallFieldGetV2Response;
import com.kusd.KUmap.domain.search.service.FieldV2SearchService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v2/field-search")
@RequiredArgsConstructor
public class FieldSearchV2Controller {

    private final FieldV2SearchService fieldV2SearchService;

    @GetMapping("/all")
    @Operation(summary = "(진출 분야 검색용) 역량이 존재하는 모든 진출 분야 리스트 조회하기")
    public ResponseEntity<Set<AllFieldGetResponse>> getFields() {
        return ResponseEntity.ok(fieldV2SearchService.getAllFieldList());
    }

    @GetMapping("/middle")
    @Operation(summary = "모든 중분류 가져오기")
    public ResponseEntity<List<MiddleFieldGetV2Response>> getMiddleFieldResponse(
    ) {
        return ResponseEntity.ok(fieldV2SearchService.getMiddleFieldList());
    }

    @PostMapping("/small")
    @Operation(summary = "진출 분야 특정 중분류에 속하는 소분류 조회하기")
    public ResponseEntity<List<SmallFieldGetV2Response>> getSmallFieldResponse(
            @RequestBody SmallFieldGetV2Request request
    ) {
        return ResponseEntity.ok(fieldV2SearchService.getSmallFieldList(request));
    }

    @PostMapping("/detail")
    @Operation(summary = "진출 분야 특정 소분류에 속하는 세분류 조회하기")
    public ResponseEntity<List<DetailFieldGetResponse>> getDetailFieldResponse(
            @RequestBody DetailFieldGetV2Request request
    ) {
        return ResponseEntity.ok(fieldV2SearchService.getDetailFieldList(request));
    }
}
