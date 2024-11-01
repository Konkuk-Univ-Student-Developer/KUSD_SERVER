package com.kusd.KUmap.domain.competitionRate.repository;

import com.kusd.KUmap.domain.competitionRate.domain.CompetitionRate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRateRepository extends JpaRepository<CompetitionRate, String> {

    List<CompetitionRate> findByHaksuId(String haksuId);
}
