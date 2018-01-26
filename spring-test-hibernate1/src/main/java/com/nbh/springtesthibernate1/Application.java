package com.nbh.springtesthibernate1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


@Service
class PreFillService{

    @Autowired
    public void PreFillSerice(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            SchoolRepo schoolRepo){

/*        Student bob= studentRepository.save(new Student("Bob"));
        Student phil= studentRepository.save(new Student("Phil"));
        Student richard = studentRepository.save(new Student("Richard"));

        Course geography = courseRepository.save(new Course("Geography"));
        Course maths = courseRepository.save(new Course("Maths"));*/
    }
}









