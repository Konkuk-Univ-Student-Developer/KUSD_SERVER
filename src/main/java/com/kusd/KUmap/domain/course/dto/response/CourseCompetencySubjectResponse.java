package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.competency.entity.Competency;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseCompetencySubjectResponse {
    String competencyCode;
    String competencyName;
    String competencyDescription;
    List<CourseSubjectGetResponse> courseGetResponseList;

    public static CourseCompetencySubjectResponse from(Competency competency) {
        return CourseCompetencySubjectResponse.builder()
            .competencyCode(competency.getCompetencyCode())
            .competencyName(competency.getCompetencyName())
            .competencyDescription(competency.getRemarks())
            .build();
    }

    public void addCourseSubjectGetResponseList(
        List<CourseSubjectGetResponse> courseGetResponseList) {
        this.courseGetResponseList = courseGetResponseList;
    }
}