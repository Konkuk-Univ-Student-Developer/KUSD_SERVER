package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.CourseDetails;
import lombok.Builder;

@Builder
public record CourseGetResponse(
    String haksuId,
    String name,
    String openingYear,
    String openingSemester
) {

    public static CourseGetResponse from(CourseDetails courseDetails) {
        return CourseGetResponse.builder()
            .haksuId(courseDetails.getHaksuId())
            .name(courseDetails.getTypicalKoreanName())
            .openingYear(courseDetails.getOpeningSchoolYear())
            .openingSemester(courseDetails.getOpeningSemesterTerm())
            .build();
    }

}
