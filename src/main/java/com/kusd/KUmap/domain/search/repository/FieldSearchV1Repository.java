package com.kusd.KUmap.domain.search.repository;

import com.kusd.KUmap.domain.search.domain.Field;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Deprecated
public interface FieldSearchV1Repository extends JpaRepository<Field, String> {


    @Query("SELECT f FROM Field f WHERE SUBSTRING(f.fieldCode, LENGTH(f.fieldCode) - 5, 6) = '000000'")
    List<Field> findAllByFieldCode();

    @Query("SELECT f FROM Field f WHERE f.largeField = :largeField AND f.middleField IS NOT NULL AND f.smallField IS NULL")
    List<Field> findAllByLargeField(String largeField);

    @Query("SELECT f FROM Field f WHERE f.largeField = :largeField AND f.middleField = :middleField AND f.smallField IS NOT NULL AND f.detailField IS NULL")
    List<Field> findAllByLargeFieldAndMiddleField(
            @Param("largeField") String largeField,
            @Param("middleField") String middleField
    );

    @Query("SELECT f FROM Field f "
            + "WHERE f.largeField = :largeField "
            + "AND f.middleField = :middleField "
            + "AND f.smallField = :smallField "
            + "AND f.detailField IS NOT NULL")
    List<Field> findAllByLargeFieldAndMiddleFieldAndSmallField(
            @Param("largeField") String largeField,
            @Param("middleField") String middleField,
            @Param("smallField") String smallField
    );
}
