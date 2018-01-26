package com.nbh.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }
}



@Service
class PrefillService {

    @Autowired
    public PrefillService(CourseRepo courseRepo) {
        courseRepo.save(new Course("Maths"));
        courseRepo.save(new Course("English"));
        courseRepo.save(new Course("French"));
    }
}


