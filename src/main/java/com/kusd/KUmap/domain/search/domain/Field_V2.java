package com.kusd.KUmap.domain.search.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "field_v2")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Field_V2 {

    @Id
    @Column(name = "FIELD_CD")
    private String fieldCode;

    @Column(name = "LT_YY")
    private String year;

    @Column(name = "FIELD_NM")
    private String name;

    @Column(name = "LVL")
    private int categoryDepth;

    @Column(name = "UP_FIELD_CD")
    private String parentFieldCode;

    @Column(name = "INPT_DT")
    private String inputDate;

    @Column(name = "INPT_ID")
    private String inputId;

    @Column(name = "UPDT_DT")
    private String updateDate;

    @Column(name = "UPDT_ID")
    private String updateId;

    @Column(name = "IP_ADDR")
    private String ipAddress;

    @Builder
    public Field_V2(String fieldCode, String year, String name, int categoryDepth, String parentFieldCode,
                    String inputDate,
                    String inputId, String updateDate, String updateId, String ipAddress) {
        this.fieldCode = fieldCode;
        this.year = year;
        this.name = name;
        this.categoryDepth = categoryDepth;
        this.parentFieldCode = parentFieldCode;
        this.inputDate = inputDate;
        this.inputId = inputId;
        this.updateDate = updateDate;
        this.updateId = updateId;
        this.ipAddress = ipAddress;
    }
}
