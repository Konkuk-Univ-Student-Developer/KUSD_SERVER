package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.AddInformation;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import lombok.Builder;

@Builder
public record CourseSubjectGetResponse(
    String haksuId,
    String name,
    Integer openingYear,
    String openingSemester,
    String openingSubject
) {

    public static CourseSubjectGetResponse from(CourseDetails courseDetails) {
        AddInformation addInformation = courseDetails.getAddInformation();
        return CourseSubjectGetResponse.builder()
            .haksuId(courseDetails.getHaksuId())
            .name(courseDetails.getTypicalKoreanName())
            .openingYear(addInformation.getOpeningSchoolYear())
            .openingSemester(addInformation.getOpeningSemesterTerm())
            .openingSubject(courseDetails.getAddInformation().getOpeningSubjectName())
            .build();
    }

}