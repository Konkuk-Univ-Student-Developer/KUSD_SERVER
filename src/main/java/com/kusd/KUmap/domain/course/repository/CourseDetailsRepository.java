package com.kusd.KUmap.domain.course.repository;

import com.kusd.KUmap.domain.course.entity.CourseDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseDetailsRepository extends JpaRepository<CourseDetails, String> {

    @Query("SELECT c FROM CourseDetails c WHERE c.competencyCode1 = :cmptCode OR c.competencyCode2 = :cmptCode OR c"
        + ".competencyCode3 = :cmptCode ")
    List<CourseDetails> findAllByCompetencyCode(@Param("cmptCode") String cmptCode);

    List<CourseDetails> findAllByOpeningSubjectCode(String openingSubjectCode);

    @Query("SELECT c FROM CourseDetails c WHERE c.openingSubjectCode = :openingSubjectCode AND (c.competencyCode1 = :cmptCode OR c.competencyCode2 = :cmptCode OR c.competencyCode3 = :cmptCode)")
    List<CourseDetails> findAllByOpeningSubjectCodeAndCompetencyCode(
        @Param("openingSubjectCode") String openingSubjectCode,
        @Param("cmptCode") String cmptCode
    );
}
