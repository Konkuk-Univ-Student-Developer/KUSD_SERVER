package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.CompetencyInCourse;

public record CompetencyInCourseGetResponse(
    String competencyCode1,
    String competencyName1,
    String competencyRemark1,
    String competencyCode2,
    String competencyName2,
    String competencyRemark2,
    String competencyCode3,
    String competencyName3,
    String competencyRemark3
) {
    public static CompetencyInCourseGetResponse from(CompetencyInCourse competency) {
        return new CompetencyInCourseGetResponse(
            competency.getCompetencyCode1(),
            competency.getCompetencyName1(),
            competency.getCompetencyRemark1(),
            competency.getCompetencyCode2(),
            competency.getCompetencyName2(),
            competency.getCompetencyRemark2(),
            competency.getCompetencyCode3(),
            competency.getCompetencyName3(),
            competency.getCompetencyRemark3()
        );
    }
}
