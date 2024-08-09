package com.kusd.KUmap.domain.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

    @Column(name = "POBT_DV")
    private String programOfBasisType;

    @Column(name = "TYPL_KOR_NM")
    private String typicalKoreanName;

    @Column(name = "TYPL_ENG_NM")
    private String typicalEnglishName;

    @Column(name = "PNT")
    private Integer point;

    @Column(name = "KOR_DESC", columnDefinition = "TEXT")
    private String koreanDescription;

    @Column(name = "ENG_DESC", columnDefinition = "TEXT")
    private String englishDescription;

    @Embedded
    private AddInformation addInformation;

    @Embedded
    private CompetencyInCourse competencyInCourse;

    @Column(name = "UDT_DT")
    private String updateDate;

    @Column(name = "DEL_DT")
    private String deletionDate;

    // Getters and setters can be added here for each field
}

