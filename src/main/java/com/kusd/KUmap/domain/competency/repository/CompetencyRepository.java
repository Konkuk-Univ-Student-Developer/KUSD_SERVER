package com.kusd.KUmap.domain.competency.repository;

import com.kusd.KUmap.domain.competency.entity.Competency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetencyRepository extends JpaRepository<Competency, String> {

    Competency findByCompetencyCode(String competencyCode);
}
