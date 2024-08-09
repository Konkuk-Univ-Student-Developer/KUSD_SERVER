package com.kusd.KUmap.domain.course.dto.response;

import com.kusd.KUmap.domain.competency.entity.Competency;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseCompetencyResponse{
    String competencyCode;
    String competencyName;
    String competencyDescription;
    List<CourseGetResponse> courseGetResponseList;

    public static CourseCompetencyResponse from(Competency competency) {
        return CourseCompetencyResponse.builder()
            .competencyCode(competency.getCompetencyCode())
            .competencyName(competency.getSubjectMajorCode())
            .competencyDescription(competency.getRemarks())
            .build();
    }

    public void addCourseGetResponseList(
        List<CourseGetResponse> courseGetResponseList) {
        this.courseGetResponseList = courseGetResponseList;
    }
}
