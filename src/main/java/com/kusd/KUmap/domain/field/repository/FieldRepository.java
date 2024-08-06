package com.kusd.KUmap.domain.field.repository;

import com.kusd.KUmap.domain.field.entity.Field;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FieldRepository extends JpaRepository<Field, String> {

    @Query("SELECT f FROM Field f WHERE SUBSTRING(f.fieldCode, LENGTH(f.fieldCode) - 5, 6) = '000000'")
    List<Field> findAllByFieldCode();

    @Query("SELECT f FROM Field f WHERE f.largeField = :largeField AND f.middleField IS NOT NULL AND f.smallField IS NULL")
    List<Field> findAllByLargeFieldAndMiddleFieldAndSmallField(String largeField);

}
