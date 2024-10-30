package com.kusd.KUmap.domain.search.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "field")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Field {

    @Id
    @Column(name = "FIELD_CD")
    private String fieldCode;

    @Column(name = "NO")
    private int number;

    @Column(name = "LARGEFIELD")
    private String largeField;

    @Column(name = "MIDDLEFIELD")
    private String middleField;

    @Column(name = "SMALLFIELD")
    private String smallField;

    @Column(name = "DETAILFIELD")
    private String detailField;

    public boolean hasMiddleField() {
        return middleField != null;
    }

    public boolean hasSmallField() {
        return smallField != null;
    }

    public boolean hasDetailField() {
        return detailField != null;
    }

    public String getFieldSearchFormat() {
        if (middleField == null) return "";

        if (smallField == null) return middleField;

        if (detailField == null) return middleField + " > " + smallField;

        return middleField + " > " + smallField + " > " + detailField;
    }
}
