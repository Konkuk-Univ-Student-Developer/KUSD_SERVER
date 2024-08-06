package com.kusd.KUmap.domain.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "coursedetails")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseDetails {

    @Id
    @Column(name = "HAKSU_ID")
    private String haksuId;

    @Column(name = "OPEN_YY")
    private String openYear;

    @Column(name = "APLY_ST_CD")
    private String applicationStatusCode;

    @Column(name = "OPEN_SHYR")
    private String openingSchoolYear;

    @Column(name = "OPEN_SHTM")
    private String openingSemesterTerm;

    @Column(name = "POBT_DV")
    private String programOfBasisType;

    @Column(name = "TYPL_KOR_NM")
    private String typicalKoreanName;

    @Column(name = "TYPL_ENG_NM")
    private String typicalEnglishName;

    @Column(name = "PNT")
    private Integer point;

    @Column(name = "TM")
    private String time;

    @Column(name = "OPEN_COLG_NM")
    private String openingCollegeName;

    @Column(name = "OPEN_COLG")
    private String openingCollegeCode;

    @Column(name = "OPEN_SUST_NM")
    private String openingSubjectName;

    @Column(name = "OPEN_SUST")
    private String openingSubjectCode;

    @Column(name = "CULT_CORS_FLD")
    private String culturalCourseField;

    @Column(name = "LT_TYPE")
    private String lectureType;

    @Column(name = "DMND_SUST")
    private String demandSubject;

    @Column(name = "THEO_TM")
    private String theoryTime;

    @Column(name = "PRAC_TM")
    private String practiceTime;

    @Column(name = "FLTM_PAY_BASI")
    private String flatTimePayBasis;

    @Column(name = "LOTS_PAY_BASI")
    private String lotsPayBasis;

    @Column(name = "CLSS_RCNT")
    private Integer classRecruitment;

    @Column(name = "DEL_YY")
    private String deletionYear;

    @Column(name = "DEL_SHTM")
    private String deletionSemesterTerm;

    @Column(name = "DEL_STS")
    private String deletionStatus;

    @Column(name = "PASS_SUBJ_FG")
    private String passSubjectFlag;

    @Column(name = "DEMAND_SURVEY")
    private String demandSurvey;

    @Column(name = "SELC_FG")
    private String selectiveFlag;

    @Column(name = "MOOC_FG")
    private String moocFlag;

    @Column(name = "PRE_APLY")
    private String prerequisitesApplication;

    @Column(name = "KOR_DESC", columnDefinition = "TEXT")
    private String koreanDescription;

    @Column(name = "ENG_DESC", columnDefinition = "TEXT")
    private String englishDescription;

    @Column(name = "ENGR_CERT_FG_CD")
    private String engineeringCertificationFlagCode;

    @Column(name = "ABEEK_FG_CD")
    private String abeekFlagCode;

    @Column(name = "ABEEK_DETA_FG_CD")
    private String abeekDetailedFlagCode;

    @Column(name = "LCLS_NM")
    private String largeClassName;

    @Column(name = "MCLS_NM")
    private String middleClassName;

    @Column(name = "SCLS_NM")
    private String smallClassName;

    @Column(name = "TAX_CLSS_NM")
    private String taxonomyClassName;

    @Column(name = "SUST_ENG_FG")
    private String subjectEnglishFlag;

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

    @Column(name = "UDT_DT")
    private String updateDate;

    @Column(name = "DEL_DT")
    private String deletionDate;

    // Getters and setters can be added here for each field
}

