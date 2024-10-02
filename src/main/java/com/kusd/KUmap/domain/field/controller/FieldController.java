package com.kusd.KUmap.domain.field.controller;

import com.kusd.KUmap.domain.field.dto.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.MiddleFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SubjectResponse;
import com.kusd.KUmap.domain.field.service.FieldService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

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

    @GetMapping("/{fields-code}/subjects")
    @Operation(summary = "진출 분야로 갈 수 있는 전공 조회하기")
    public ResponseEntity<Set<SubjectResponse>> getSubjectsByFieldCode(
        @PathVariable("fields-code") String fieldCode
    ) {
        return ResponseEntity.ok(fieldService.getSubjectListByField(fieldCode));
    }
}
