package com.nbh.relationships.onetomany.repos;

import com.nbh.relationships.onetomany.model.Pupil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepo extends CrudRepository<Pupil, Long> {

    Pupil findByName(@Param("name") String name);
}
