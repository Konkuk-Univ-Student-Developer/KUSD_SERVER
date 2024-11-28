package com.kusd.KUmap.domain.competitionRate.api;

import com.kusd.KUmap.domain.competitionRate.dto.response.CompetitionRateResponse;
import com.kusd.KUmap.domain.competitionRate.service.CompetitionRateService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/competition-rate")
@RequiredArgsConstructor
public class CompetitionRateController {

    private final CompetitionRateService competitionRateService;

    @GetMapping("/{haksuId}")
    @Operation(summary = "학수 번호로 경쟁률 조회")
    public ResponseEntity<List<CompetitionRateResponse>> getCompetitionRate(
            @PathVariable("haksuId") String haksuId
    ) {
        return ResponseEntity.ok(competitionRateService.getCompetitionRate(haksuId));
    }
}
