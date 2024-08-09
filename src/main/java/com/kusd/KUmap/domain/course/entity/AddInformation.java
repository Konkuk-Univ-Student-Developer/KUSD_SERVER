package com.kusd.KUmap.domain.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddInformation {

    @Column(name = "OPEN_SUST")
    private String openingSubjectCode;

    @Column(name = "OPEN_COLG_NM")
    private String openingCollegeName;

    @Column(name = "OPEN_SUST_NM")
    private String openingSubjectName;

    @Column(name = "LT_TYPE")
    private String lectureType;

    @Column(name = "OPEN_SHYR")
    private Integer openingSchoolYear;

    @Column(name = "OPEN_SHTM")
    private String openingSemesterTerm;

    @Column(name = "TM")
    private Integer time;

    @Column(name = "PRE_APLY")
    private String prerequisitesApplication;

    @Column(name = "ENGR_CERT_FG_CD")
    private String engineeringCertificationFlagCode;

    @Column(name = "MOOC_FG")
    private Integer moocFlag;

    @Column(name = "dream_shtm_fg")
    private boolean dreamSemesterFlag;

    @Column(name = "SELC_FG")
    private Integer selectiveFlag;

}
