package com.kusd.KUmap.domain.competitionRate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CompetitionRate {

    @Id
    @Column(name = "competition_rate_id")
    private String id;

    private String haksuId;

    private String year;

    private String professor;

    private String lectureTime;

    private String totalApplicationNumber;

    private String undergraduateNumber;

    private String postgraduateNumber;

    private int totalNumber;
    private int totalCourseBasketNumber;
    private double totalCompetitionRate;

    private int freshmanTotalNumber;
    private int freshmanCourseBasketNumber;
    private double freshmanCompetitionRate;

    private int sophomoreTotalNumber;
    private int sophomoreCourseBasketNumber;
    private double sophomoreCompetitionRate;

    private int juniorTotalNumber;
    private int juniorCourseBasketNumber;
    private double juniorCompetitionRate;

    private int seniorTotalNumber;
    private int seniorCourseBasketNumber;
    private double seniorCompetitionRate;

    public CompetitionRate(String id, String haksuId, String year, String professor, String lectureTime,
                           String totalApplicationNumber, String undergraduateNumber, String postgraduateNumber,
                           int totalNumber, int totalCourseBasketNumber, double totalCompetitionRate,
                           int freshmanTotalNumber, int freshmanCourseBasketNumber, double freshmanCompetitionRate,
                           int sophomoreTotalNumber, int sophomoreCourseBasketNumber, double sophomoreCompetitionRate,
                           int juniorTotalNumber, int juniorCourseBasketNumber, double juniorCompetitionRate,
                           int seniorTotalNumber, int seniorCourseBasketNumber, double seniorCompetitionRate) {
        this.id = id;
        this.haksuId = haksuId;
        this.year = year;
        this.professor = professor;
        this.lectureTime = lectureTime;
        this.totalApplicationNumber = totalApplicationNumber;
        this.undergraduateNumber = undergraduateNumber;
        this.postgraduateNumber = postgraduateNumber;
        this.totalNumber = totalNumber;
        this.totalCourseBasketNumber = totalCourseBasketNumber;
        this.totalCompetitionRate = totalCompetitionRate;
        this.freshmanTotalNumber = freshmanTotalNumber;
        this.freshmanCourseBasketNumber = freshmanCourseBasketNumber;
        this.freshmanCompetitionRate = freshmanCompetitionRate;
        this.sophomoreTotalNumber = sophomoreTotalNumber;
        this.sophomoreCourseBasketNumber = sophomoreCourseBasketNumber;
        this.sophomoreCompetitionRate = sophomoreCompetitionRate;
        this.juniorTotalNumber = juniorTotalNumber;
        this.juniorCourseBasketNumber = juniorCourseBasketNumber;
        this.juniorCompetitionRate = juniorCompetitionRate;
        this.seniorTotalNumber = seniorTotalNumber;
        this.seniorCourseBasketNumber = seniorCourseBasketNumber;
        this.seniorCompetitionRate = seniorCompetitionRate;
    }
}
