package com.kusd.KUmap.domain.search.repository;

import com.kusd.KUmap.domain.search.domain.Field_V2;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldV2SearchRepository extends JpaRepository<Field_V2, Long> {

    List<Field_V2> findAllFieldByCategoryDepth(int categoryDepth);

    List<Field_V2> findAllSmallFieldByCategoryDepthAndParentFieldCode(int categoryDepth, String parentFieldCode);

    List<Field_V2> findAllByParentFieldCode(String parentFieldCode);

    Optional<Field_V2> findByFieldCode(String fieldCode);
}
