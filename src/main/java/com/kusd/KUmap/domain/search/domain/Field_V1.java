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
@Deprecated
@Table(name = "field_v1")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Field_V1 {

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

    public boolean hasDetailField() {
        return detailField != null;
    }
}
