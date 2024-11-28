package com.kusd.KUmap.domain.competitionRate.dto.response;

import com.kusd.KUmap.domain.competitionRate.domain.CompetitionRate;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

public record CompetitionRateResponse(
    String year,
    @Schema(example = "BBAB54724")
    String haksuId,
    String openingSemester,
    int totalApplicationNumber,
    Map<String, Double> competitionRates
) {

    public static CompetitionRateResponse from(CompetitionRate entity, int totalApplicationNumber, Map<String, Double> competitionRates) {
        return new CompetitionRateResponse(
                entity.getYear(),
                entity.getHaksuId(),
                entity.getOpenSemester(),
                totalApplicationNumber,
                competitionRates
        );
    }
}
