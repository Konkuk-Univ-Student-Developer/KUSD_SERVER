package com.kusd.KUmap.domain.field.controller;

import com.kusd.KUmap.domain.field.dto.response.LargeFieldGetResponse;
import com.kusd.KUmap.domain.field.service.FieldService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
