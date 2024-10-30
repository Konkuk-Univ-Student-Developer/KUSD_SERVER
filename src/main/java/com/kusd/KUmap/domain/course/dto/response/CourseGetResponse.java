package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.AddInformation;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import lombok.Builder;

@Builder
public record CourseGetResponse(
        String haksuId,
        String name,
        Integer openingYear,
        String openingSemester,
        int credit
) {

    public static CourseGetResponse from(CourseDetails courseDetails) {
        AddInformation addInformation = courseDetails.getAddInformation();

        return CourseGetResponse.builder()
                .haksuId(courseDetails.getHaksuId())
                .name(courseDetails.getTypicalKoreanName())
                .openingYear(addInformation.getOpeningSchoolYear())
                .openingSemester(addInformation.getOpeningSemesterTerm())
                .credit(courseDetails.getPoint())
                .build();
    }

}
