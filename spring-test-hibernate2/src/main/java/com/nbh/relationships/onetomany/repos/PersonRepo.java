package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
    Person findByName(@Param("name") String name);
}
