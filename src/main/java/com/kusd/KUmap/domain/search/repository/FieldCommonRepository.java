package com.kusd.KUmap.domain.search.repository;

import com.kusd.KUmap.domain.search.domain.Field;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldCommonRepository extends JpaRepository<Field, String> {

    Optional<Field> findByFieldCode(String fieldCode);

}
