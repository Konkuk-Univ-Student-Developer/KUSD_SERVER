package com.kusd.KUmap.domain.course.controller;

import com.kusd.KUmap.domain.course.dto.response.CourseCompetencyResponse;
import com.kusd.KUmap.domain.course.dto.response.CourseCompetencySubjectResponse;
import com.kusd.KUmap.domain.course.dto.response.CourseGetResponse;
import com.kusd.KUmap.domain.course.service.CourseDetailService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/{subject-code}/subject")
    @Operation(summary = "학과 내 모든 강좌 리스트")
    public ResponseEntity<List<CourseGetResponse>> getCourseListBySubject(
        @PathVariable("subject-code") String subjectCode
    ) {
        return ResponseEntity.ok(courseDetailService.getCourseListBySubject(subjectCode));
    }

    @GetMapping("/{field-code}/field")
    @Operation(summary = "진출분야로만 각 역량에 해당하는 강좌 필터링하기")
    public ResponseEntity<List<CourseCompetencySubjectResponse>> getCourseListByField(
        @PathVariable("field-code") String fieldCode
    ) {
        return ResponseEntity.ok(courseDetailService.getCourseListByField(fieldCode));
    }

    @GetMapping("/{subject-code}/{field-code}")
    @Operation(summary = "진출분야 + 학과로 각 역량에 해당하는 강좌 필터링하기")
    public ResponseEntity<List<CourseCompetencyResponse>> getCourseListBySubjectAndField(
        @PathVariable("subject-code") String subjectCode,
        @PathVariable("field-code") String fieldCode
    ) {
        return ResponseEntity.ok(courseDetailService.getCourseListBySubjectAndField(subjectCode, fieldCode));
    }
}
