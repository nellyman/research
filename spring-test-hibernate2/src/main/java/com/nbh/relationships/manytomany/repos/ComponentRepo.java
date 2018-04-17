package com.nbh.relationships.manytomany.repos;

import com.nbh.relationships.manytomany.model.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepo extends CrudRepository<Component, Long> {

}
