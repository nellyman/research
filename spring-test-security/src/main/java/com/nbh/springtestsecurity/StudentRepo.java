package com.nbh.springtestsecurity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student, Long> {


    Student findByName(String name);
}
