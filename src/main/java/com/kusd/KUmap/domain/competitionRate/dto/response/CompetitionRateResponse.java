package com.kusd.KUmap.domain.competitionRate.dto.response;

import com.kusd.KUmap.domain.competitionRate.domain.CompetitionRate;
import java.util.Map;

public record CompetitionRateResponse(
    String year,
    String haksuId,
    String professor,
    String totalApplicationNumber,
    String undergraduateNumber,
    String postgraduateNumber,
    String lectureTime,
    Map<String, Double> total,
    Map<String, Double> freshman,
    Map<String, Double> sophomore,
    Map<String, Double> junior,
    Map<String, Double> senior
) {

    public static CompetitionRateResponse from(CompetitionRate competitionRate) {
        return new CompetitionRateResponse(
                competitionRate.getYear(),
                competitionRate.getHaksuId(),
                competitionRate.getProfessor(),
                competitionRate.getTotalApplicationNumber(),
                competitionRate.getUndergraduateNumber(),
                competitionRate.getPostgraduateNumber(),
                competitionRate.getLectureTime(),
                Map.of(
                        "totalNumber", (double) competitionRate.getTotalNumber(),
                        "totalCourseBasketNumber", (double) competitionRate.getTotalCourseBasketNumber(),
                        "totalCompetitionRate", competitionRate.getTotalCompetitionRate()
                        // assuming this is already double
                ),
                Map.of(
                        "freshmanTotalNumber", (double) competitionRate.getFreshmanTotalNumber(),
                        "freshmanCourseBasketNumber", (double) competitionRate.getFreshmanCourseBasketNumber(),
                        "freshmanCompetitionRate", competitionRate.getFreshmanCompetitionRate()
                        // assuming this is already double
                ),
                Map.of(
                        "sophomoreTotalNumber", (double) competitionRate.getSophomoreTotalNumber(),
                        "sophomoreCourseBasketNumber", (double) competitionRate.getSophomoreCourseBasketNumber(),
                        "sophomoreCompetitionRate", competitionRate.getSophomoreCompetitionRate()
                        // assuming this is already double
                ),
                Map.of(
                        "juniorTotalNumber", (double) competitionRate.getJuniorTotalNumber(),
                        "juniorCourseBasketNumber", (double) competitionRate.getJuniorCourseBasketNumber(),
                        "juniorCompetitionRate", competitionRate.getJuniorCompetitionRate()
                        // assuming this is already double
                ),
                Map.of(
                        "seniorTotalNumber", (double) competitionRate.getSeniorTotalNumber(),
                        "seniorCourseBasketNumber", (double) competitionRate.getSeniorCourseBasketNumber(),
                        "seniorCompetitionRate", competitionRate.getSeniorCompetitionRate()
                        // assuming this is already double
                )
        );
    }
}
