package com.kusd.KUmap.domain.competitionRate.service;

import com.kusd.KUmap.domain.competitionRate.domain.CompetitionRate;
import com.kusd.KUmap.domain.competitionRate.dto.response.CompetitionRateResponse;
import com.kusd.KUmap.domain.competitionRate.repository.CompetitionRateRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionRateService {
    private static final double ROUNDING_FACTOR = 1000.0;

    private final CompetitionRateRepository competitionRateRepository;

    public CompetitionRateResponse getCompetitionRate(String haksuId) {
        List<CompetitionRate> competitionRates = competitionRateRepository.findByHaksuId(haksuId);
        int totalApplicationNumber = getTotalApplicationNumber(competitionRates);
        Map<String, Double> averageTotalCompetitionRate = calculateAverageCompetitionRate(competitionRates);

        return  CompetitionRateResponse.from(competitionRates.get(0), totalApplicationNumber, averageTotalCompetitionRate);
    }

    private static int getTotalApplicationNumber(List<CompetitionRate> competitionRates) {
        return competitionRates.stream()
                .map(CompetitionRate::getUndergraduateNumber)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private Map<String, Double> calculateAverageCompetitionRate(List<CompetitionRate> competitionRates) {
        Map<String, Double> averageCompetitionRate = new HashMap<>();

        for (CompetitionRate competitionRate : competitionRates) {
            // Total
            double totalNum = sumNumber("totalNumber", competitionRate.getTotalNumber(), averageCompetitionRate);
            double totalBasketNum = sumNumber("totalCourseBasketNumber", competitionRate.getTotalCourseBasketNumber(), averageCompetitionRate);
            averageCompetitionRate.put("totalCompetitionRate", getCompetitionRate(totalBasketNum, totalNum));

            // Freshman
            double freshmanNum = sumNumber("freshmanTotalNumber", competitionRate.getFreshmanTotalNumber(), averageCompetitionRate);
            double freshmanBasketNum = sumNumber("freshmanCourseBasketNumber", competitionRate.getFreshmanCourseBasketNumber(), averageCompetitionRate);
            averageCompetitionRate.put("freshmanCompetitionRate", getCompetitionRate(freshmanBasketNum, freshmanNum));

            // Sophomore
            double sophomoreNum = sumNumber("sophomoreTotalNumber", competitionRate.getSophomoreTotalNumber(), averageCompetitionRate);
            double sophomoreBasketNum = sumNumber("sophomoreCourseBasketNumber", competitionRate.getSophomoreCourseBasketNumber(), averageCompetitionRate);
            averageCompetitionRate.put("sophomoreCompetitionRate", getCompetitionRate(sophomoreBasketNum, sophomoreNum));

            // Junior
            double juniorNum = sumNumber("juniorTotalNumber", competitionRate.getJuniorTotalNumber(), averageCompetitionRate);
            double juniorBasketNum = sumNumber("juniorCourseBasketNumber", competitionRate.getJuniorCourseBasketNumber(), averageCompetitionRate);
            averageCompetitionRate.put("juniorCompetitionRate", getCompetitionRate(juniorBasketNum, juniorNum));

            // Senior
            double seniorNum = sumNumber("seniorTotalNumber", competitionRate.getSeniorTotalNumber(), averageCompetitionRate);
            double seniorBasketNum = sumNumber("seniorCourseBasketNumber", competitionRate.getSeniorCourseBasketNumber(), averageCompetitionRate);
            averageCompetitionRate.put("seniorCompetitionRate", getCompetitionRate(seniorBasketNum, seniorNum));
        }
        return averageCompetitionRate;
    }

    private static double getCompetitionRate(double totalCourseBasketNumber, double sumTotalNum) {
        double v = totalCourseBasketNumber / sumTotalNum;
        return Math.round(v * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    private static double sumNumber(String key, int value, Map<String, Double> averageCompetitionRate) {
        return averageCompetitionRate.getOrDefault(key, 0.0) + value;
    }
}
