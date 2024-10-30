package com.kusd.KUmap.domain.search.api;

import com.kusd.KUmap.domain.search.dto.subjectSearch.response.SubjectResponse;
import com.kusd.KUmap.domain.search.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subject-search")
@RequiredArgsConstructor
public class SubjectSearchController {

    private final SubjectService subjectService;

    @GetMapping("/{fields-code}/subjects")
    @Operation(summary = "진출 분야로 갈 수 있는 전공 조회하기")
    public ResponseEntity<Set<SubjectResponse>> getSubjectsByFieldCode(
            @PathVariable("fields-code") String fieldCode
    ) {
        return ResponseEntity.ok(subjectService.getSubjectListByField(fieldCode));
    }
}
