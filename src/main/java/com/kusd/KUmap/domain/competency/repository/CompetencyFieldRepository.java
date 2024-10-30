package com.kusd.KUmap.domain.competency.repository;

import com.kusd.KUmap.domain.competency.entity.CompetencyField;
import com.kusd.KUmap.domain.search.domain.Field;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetencyFieldRepository extends JpaRepository<CompetencyField, Long> {

    List<CompetencyField> findAllByField(Field field);
    boolean existsByField(Field field);
}
