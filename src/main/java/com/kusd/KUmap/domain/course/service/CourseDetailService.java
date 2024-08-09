package com.kusd.KUmap.domain.course.service;

import com.kusd.KUmap.domain.course.dto.response.CourseGetResponse;
import com.kusd.KUmap.domain.course.entity.CourseDetails;
import com.kusd.KUmap.domain.course.repository.CourseDetailsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseDetailService {

    private final CourseDetailsRepository courseDetailsRepository;

    public List<CourseDetails> getCourseListByCompetencyCode(String cmptCode) {
        return courseDetailsRepository.findAllByCompetencyCode(cmptCode);
    }

    public List<CourseGetResponse> getCourseListBySubject(String subjectCode) {

        return courseDetailsRepository.findAllByOpeningSubjectCode(subjectCode).stream()
            .map(CourseGetResponse::from)
            .toList();
    }

    public void getCourseListByField(){

    }

    public void getCourseListBySubjectAndField(){

    }

    public void getCourseDetails(String haksuId) {

    }
}
