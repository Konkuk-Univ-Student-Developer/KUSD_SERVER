package com.kusd.KUmap.domain.course.repository;

import com.kusd.KUmap.domain.course.entity.CourseDetails;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseDetailsRepository extends JpaRepository<CourseDetails, String> {

    @Query("SELECT c FROM CourseDetails c WHERE c.competencyInCourse.competencyCode1 = :cmptCode OR c.competencyInCourse.competencyCode2 = :cmptCode OR c"
        + ".competencyInCourse.competencyCode3 = :cmptCode ")
    List<CourseDetails> findAllByCompetencyCode(@Param("cmptCode") String cmptCode);

    @Query("SELECT c FROM CourseDetails c WHERE c.addInformation.openingSubjectCode = :openingSubjectCode")
    List<CourseDetails> findAllByOpeningSubjectCode(@Param("openingSubjectCode") String openingSubjectCode);

    @Query("SELECT c FROM CourseDetails c WHERE c.addInformation.openingSubjectCode = :openingSubjectCode AND (c.competencyInCourse"
        + ".competencyCode1 = :cmptCode OR c.competencyInCourse.competencyCode2 = :cmptCode OR c.competencyInCourse.competencyCode3 = :cmptCode)")
    List<CourseDetails> findAllByOpeningSubjectCodeAndCompetencyCode(
        @Param("openingSubjectCode") String openingSubjectCode,
        @Param("cmptCode") String cmptCode
    );

    Optional<CourseDetails> findByHaksuId(String haksuId);
}
