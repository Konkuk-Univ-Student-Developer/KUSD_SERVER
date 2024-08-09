package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.CourseDetails;

public record CourseGetDetailsResponse(
    String typicalKoreanName,
    String typicalEnglishName,
    String koreanDescription,
    String englishDescription,
    AddInformationGetResponse addInformationGetResponse,
    CompetencyInCourseGetResponse competencyInCourseGetResponse
) {
    public static CourseGetDetailsResponse from(CourseDetails courseDetails) {
        return new CourseGetDetailsResponse(
            courseDetails.getTypicalKoreanName(),
            courseDetails.getTypicalEnglishName(),
            courseDetails.getKoreanDescription(),
            courseDetails.getEnglishDescription(),
            AddInformationGetResponse.from(courseDetails.getAddInformation()),
            CompetencyInCourseGetResponse.from(courseDetails.getCompetencyInCourse())
        );
    }
}

