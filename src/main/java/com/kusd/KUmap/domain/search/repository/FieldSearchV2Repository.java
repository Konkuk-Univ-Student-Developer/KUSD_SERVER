package com.kusd.KUmap.domain.search.repository;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldSearchV2Repository extends JpaRepository<Field_V1, String> {

    Optional<Field_V1> findByFieldCode(String fieldCode);

    @Query("SELECT f FROM Field_V1 f WHERE f.detailField IS NOT NULL")
    List<Field_V1> findAllWithNonEmptyDetailField();

    @Query("SELECT f FROM Field_V1 f WHERE f.middleField = :middleField AND f.smallField IS NOT NULL AND f.detailField IS NULL")
    List<Field_V1> findAllByMiddleField(
        @Param("middleField") String middleField
    );

    @Query("SELECT f FROM Field_V1 f "
            + "WHERE f.middleField = :middleField "
            + "AND f.detailField IS NOT NULL")
    List<Field_V1> findAllByMiddleFieldAndNonEmptyDetailField(
            @Param("middleField") String middleField
    );

    @Query("SELECT f FROM Field_V1 f "
        + "WHERE f.middleField = :middleField "
        + "AND f.smallField = :smallField "
        + "AND f.detailField IS NOT NULL")
    List<Field_V1> findAllByMiddleFieldAndSmallField(
        @Param("middleField") String middleField,
        @Param("smallField") String smallField
    );

}
