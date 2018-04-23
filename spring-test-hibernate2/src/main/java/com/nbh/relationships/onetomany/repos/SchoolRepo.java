package com.nbh.relationships.onetomany.repos;


import com.nbh.relationships.onetomany.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepo extends CrudRepository<School, Long> {

    School findByName(@Param("name") String name);
}