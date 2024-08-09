package com.kusd.KUmap.domain.field.controller;

import com.kusd.KUmap.domain.field.dto.request.DetailFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.MiddleFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.request.SmallFieldGetRequest;
import com.kusd.KUmap.domain.field.dto.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.MiddleFieldGetResponse;
import com.kusd.KUmap.domain.field.dto.response.SmallFieldGetResponse;
import com.kusd.KUmap.domain.field.service.FieldService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping("/large")
    public ResponseEntity<List<LargeFieldGetResponse>> getLargeFieldResponse() {
        return ResponseEntity.ok(fieldService.getLargeFieldList());
    }

    @PostMapping("/middle")
    public ResponseEntity<List<MiddleFieldGetResponse>> getMiddleFieldResponse(
        @RequestBody MiddleFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldService.getMiddleFieldList(request));
    }

    @PostMapping("/small")
    public ResponseEntity<List<SmallFieldGetResponse>> getSmallFieldResponse(
        @RequestBody SmallFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldService.getSmallFieldList(request));
    }

    @PostMapping("/detail")
    public ResponseEntity<List<DetailFieldGetResponse>> getDetailFieldResponse(
        @RequestBody DetailFieldGetRequest request
    ) {
        return ResponseEntity.ok(fieldService.getDetailFieldList(request));
    }

    @GetMapping("/{fields-code}/subjects")
    public ResponseEntity<Set<String>> getSubjectsByFieldCode(
        @PathVariable("fields-code") String fieldCode
    ) {
        return ResponseEntity.ok(fieldService.getSubjectListByField(fieldCode));
    }
}
