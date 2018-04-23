package com.nbh.relationships.manytomany.repos;

import com.nbh.relationships.manytomany.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends CrudRepository<Country, Long> {

}
