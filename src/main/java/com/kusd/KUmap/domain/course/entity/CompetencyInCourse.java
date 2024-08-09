package com.kusd.KUmap.domain.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompetencyInCourse {

    @Column(name = "CMPT_CD1")
    private String competencyCode1;

    @Column(name = "CMPT_NM1")
    private String competencyName1;

    @Column(name = "CMPT_REMK1")
    private String competencyRemark1;

    @Column(name = "CMPT_CD2")
    private String competencyCode2;

    @Column(name = "CMPT_NM2")
    private String competencyName2;

    @Column(name = "CMPT_REMK2")
    private String competencyRemark2;

    @Column(name = "CMPT_CD3")
    private String competencyCode3;

    @Column(name = "CMPT_NM3")
    private String competencyName3;

    @Column(name = "CMPT_REMK3")
    private String competencyRemark3;
}
