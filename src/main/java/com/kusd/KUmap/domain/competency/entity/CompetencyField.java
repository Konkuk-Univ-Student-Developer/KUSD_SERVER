package com.kusd.KUmap.domain.competency.entity;

import com.kusd.KUmap.domain.search.domain.Field;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "competency_field")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompetencyField {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CMPT_CD")
    private Competency competency;

    @ManyToOne
    @JoinColumn(name = "FIELD_CD")
    private Field field;

    @Column(name = "LT_YY")
    private String learningTermYear;

    @Column(name = "FIELD")
    private String fieldString;

    @Column(name = "SEQ")
    private String sequence;

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

    // Getters and setters can be added here
}

