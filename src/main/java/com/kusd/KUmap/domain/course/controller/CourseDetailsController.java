package com.kusd.KUmap.domain.course.controller;

import com.kusd.KUmap.domain.course.dto.response.CourseGetResponse;
import com.kusd.KUmap.domain.course.service.CourseDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseDetailsController {

    private final CourseDetailService courseDetailService;

    @GetMapping("/{subject-code}")
    public ResponseEntity<List<CourseGetResponse>> getCourseListBySubject(
        @PathVariable("subject-code") String subjectCode
    ) {
        return ResponseEntity.ok(courseDetailService.getCourseListBySubject(subjectCode));
    }
}
