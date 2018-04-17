package com.nbh.hibernatesearch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {


}
