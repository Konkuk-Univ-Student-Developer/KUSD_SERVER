package com.kusd.KUmap.domain.search.api;

import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request.MiddleFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v1.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.subjectSearch.response.SubjectResponse;
import com.kusd.KUmap.domain.search.service.FieldSearchV1Service;
import com.kusd.KUmap.domain.search.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/fields")
@RequiredArgsConstructor
@Deprecated
public class FieldSearchV1Controller {

    private final FieldSearchV1Service fieldService;

    private final SubjectService subjectService;

    @GetMapping("/{fields-code}/subjects")
    @Operation(summary = "진출 분야로 갈 수 있는 전공 조회하기")
    public ResponseEntity<Set<SubjectResponse>> getSubjectsByFieldCode(
            @PathVariable("fields-code") String fieldCode
    ) {
        return ResponseEntity.ok(subjectService.getSubjectListByField(fieldCode));
    }

    @GetMapping("/all")
    @Operation(summary = "진출 분야 검색용으로 전부 조회하기")
    public ResponseEntity<List<String>> getAllFieldResponse() {
        return ResponseEntity.ok(fieldService.getAllFieldList());
    }
    @GetMapping("/large")
    @Operation(summary = "진출 분야 대분류 조회하기")
    public ResponseEntity<List<LargeFieldGetResponse>> getLargeFieldResponse() {
        return ResponseEntity.ok(fieldService.getLargeFieldList());
    }
    @PostMapping("/middle")
    @Operation(summary = "진출 분야 특정 대분류에 속하는 중분류 조회하기")
    public ResponseEntity<List<MiddleFieldGetResponse>> getMiddleFieldResponse(
            @RequestBody MiddleFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldService.getMiddleFieldList(request));
    }
    @PostMapping("/small")
    @Operation(summary = "진출 분야 특정 중분류에 속하는 소분류 조회하기")
    public ResponseEntity<List<SmallFieldGetResponse>> getSmallFieldResponse(
            @RequestBody SmallFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldService.getSmallFieldList(request));
    }
    @PostMapping("/detail")
    @Operation(summary = "진출 분야 특정 소분류에 속하는 세분류 조회하기")
    public ResponseEntity<List<DetailFieldGetResponse>> getDetailFieldResponse(
            @RequestBody DetailFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldService.getDetailFieldList(request));
    }


}
