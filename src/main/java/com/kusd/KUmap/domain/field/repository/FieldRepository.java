package com.kusd.KUmap.domain.field.repository;

import com.kusd.KUmap.domain.field.entity.Field;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FieldRepository extends JpaRepository<Field, String> {

    @Query("SELECT f FROM Field f WHERE SUBSTRING(f.FieldCode, LENGTH(f.FieldCode) - 5, 6) = '000000'")
    List<Field> findAllByFieldCode();

}
