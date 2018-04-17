package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.Janitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JanitorRepo extends CrudRepository<Janitor, Long> {

    Janitor findByName(@Param("name") String name);
}
