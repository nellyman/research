package com.nbh.relationships.manytomany.repos;

import com.nbh.relationships.manytomany.model.River;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiverRepo extends CrudRepository<River, Long> {

    River findByName(String name);
}

