package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.CourseDetails;
import lombok.Builder;

@Builder
public record CourseSubjectGetResponse(
    String haksuId,
    String name,
    String openingYear,
    String openingSemester,
    String openingSubject
) {

    public static CourseSubjectGetResponse from(CourseDetails courseDetails) {
        return CourseSubjectGetResponse.builder()
            .haksuId(courseDetails.getHaksuId())
            .name(courseDetails.getTypicalKoreanName())
            .openingYear(courseDetails.getOpeningSchoolYear())
            .openingSemester(courseDetails.getOpeningSemesterTerm())
            .openingSubject(courseDetails.getOpeningSubjectName())
            .build();
    }

}
