package com.kusd.KUmap.domain.search.api;

import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.search.service.FieldSearchV2Service;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
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

    private final FieldSearchV2Service fieldSearchService;

    @GetMapping("/all")
    @Operation(summary = "(진출 분야 검색용) 역량이 존재하는 모든 진출 분야 리스트 조회하기")
    public ResponseEntity<List<String>> getFields() {
        return ResponseEntity.ok(fieldSearchService.getAllFieldList());
    }

    @GetMapping("/middle")
    @Operation(summary = "모든 중분류 가져오기")
    public ResponseEntity<List<MiddleFieldGetResponse>> getMiddleFieldResponse(
    ) {
        return ResponseEntity.ok(fieldSearchService.getMiddleFieldList());
    }

    @PostMapping("/small")
    @Operation(summary = "진출 분야 특정 중분류에 속하는 소분류 조회하기")
    public ResponseEntity<List<SmallFieldGetResponse>> getSmallFieldResponse(
            @RequestBody SmallFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldSearchService.getSmallFieldList(request));
    }

    @PostMapping("/detail")
    @Operation(summary = "진출 분야 특정 소분류에 속하는 세분류 조회하기")
    public ResponseEntity<List<DetailFieldGetResponse>> getDetailFieldResponse(
            @RequestBody DetailFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldSearchService.getDetailFieldList(request));
    }
}
