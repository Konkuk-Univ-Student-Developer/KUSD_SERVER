package com.kusd.KUmap.domain.field.dto.response;

import lombok.Builder;

@Builder
public record SubjectResponse(
    String subjectName,
    String subjectCode
) {

    public static SubjectResponse of(String subjectName, String subjectCode) {
        return SubjectResponse.builder()
            .subjectName(subjectName)
            .subjectCode(subjectCode)
            .build();
    }
}
