package com.nbh.springmvc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  CourseRepo extends CrudRepository<Course, Long> {

    Course findByName(@Param("name") String name);
}