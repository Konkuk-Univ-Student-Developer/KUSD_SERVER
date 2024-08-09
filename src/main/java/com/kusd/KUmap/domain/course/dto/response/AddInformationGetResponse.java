package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.course.entity.AddInformation;

public record AddInformationGetResponse(
    String openingSubjectCode,
    String openingCollegeName,
    String openingSubjectName,
    String lectureType,
    Integer openingSchoolYear,
    String openingSemesterTerm,
    Integer time,
    String preCourse,
    String engineeringCertificationFlagCode,
    Integer moocFlag,
    boolean dreamSemesterFlag,
    Integer selectiveFlag
) {
    public static AddInformationGetResponse from(AddInformation addInformation) {
        return new AddInformationGetResponse(
            addInformation.getOpeningSubjectCode(),
            addInformation.getOpeningCollegeName(),
            addInformation.getOpeningSubjectName(),
            addInformation.getLectureType(),
            addInformation.getOpeningSchoolYear(),
            addInformation.getOpeningSemesterTerm(),
            addInformation.getTime(),
            addInformation.getPrerequisitesApplication(),
            addInformation.getEngineeringCertificationFlagCode(),
            addInformation.getMoocFlag(),
            addInformation.isDreamSemesterFlag(),
            addInformation.getSelectiveFlag()
        );
    }
}
