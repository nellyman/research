package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.JetPart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo extends CrudRepository<JetPart, Long> {

    JetPart findByName(@Param("name") String name);
}
