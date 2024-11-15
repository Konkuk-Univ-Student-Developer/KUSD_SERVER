package com.kusd.KUmap.domain.search.repository;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Deprecated
public interface FieldSearchV1Repository extends JpaRepository<Field_V1, String> {


    @Query("SELECT f FROM Field_V1 f WHERE SUBSTRING(f.fieldCode, LENGTH(f.fieldCode) - 5, 6) = '000000'")
    List<Field_V1> findAllByFieldCode();

    @Query("SELECT f FROM Field_V1 f WHERE f.largeField = :largeField AND f.middleField IS NOT NULL AND f.smallField IS NULL")
    List<Field_V1> findAllByLargeField(String largeField);

    @Query("SELECT f FROM Field_V1 f WHERE f.largeField = :largeField AND f.middleField = :middleField AND f.smallField IS NOT NULL AND f.detailField IS NULL")
    List<Field_V1> findAllByLargeFieldAndMiddleField(
            @Param("largeField") String largeField,
            @Param("middleField") String middleField
    );

    @Query("SELECT f FROM Field_V1 f "
            + "WHERE f.largeField = :largeField "
            + "AND f.middleField = :middleField "
            + "AND f.smallField = :smallField "
            + "AND f.detailField IS NOT NULL")
    List<Field_V1> findAllByLargeFieldAndMiddleFieldAndSmallField(
            @Param("largeField") String largeField,
            @Param("middleField") String middleField,
            @Param("smallField") String smallField
    );
}
