package com.kusd.KUmap.domain.search.repository;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldCommonRepository extends JpaRepository<Field_V1, String> {

    Optional<Field_V1> findByFieldCode(String fieldCode);

}
