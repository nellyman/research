package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.Course;
import com.nbh.relationships.onetomany.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findByNameIgnoreCase(@Param("name") String name);

    List<Course> findBySchool(@Param("school") School school);
}
