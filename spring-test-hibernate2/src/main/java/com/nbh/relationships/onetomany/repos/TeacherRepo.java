package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends CrudRepository<Teacher, Long> {

    Teacher findByName(@Param("name") String name);
}
