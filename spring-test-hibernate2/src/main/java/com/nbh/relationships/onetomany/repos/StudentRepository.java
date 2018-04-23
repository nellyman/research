package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByName(@Param("name") String name);
}
