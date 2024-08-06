package com.kusd.KUmap.domain.competency.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Getter
@Entity
@Table(name = "competency")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Competency {

    @Id
    @Column(name = "CMPT_CD")
    private String competencyCode;

    @Column(name = "CMPT_NM")
    private String competencyName;

    @Column(name = "LT_YY")
    private String learningTermYear;

    @Column(name = "SUSTMJ_CD")
    private String subjectMajorCode;

    @Column(name = "REMK")
    private String remarks;

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

