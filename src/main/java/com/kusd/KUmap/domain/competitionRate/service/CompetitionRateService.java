package com.kusd.KUmap.domain.competitionRate.service;

import com.kusd.KUmap.domain.competitionRate.domain.CompetitionRate;
import com.kusd.KUmap.domain.competitionRate.dto.response.CompetitionRateResponse;
import com.kusd.KUmap.domain.competitionRate.repository.CompetitionRateRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionRateService {

    private final CompetitionRateRepository competitionRateRepository;

    public List<CompetitionRateResponse> getCompetitionRate(String haksuId) {
        return competitionRateRepository.findByHaksuId(haksuId).stream()
                .map(CompetitionRateResponse::from)
                .toList();
    }

}
