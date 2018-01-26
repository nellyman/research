package com.nbh.springtesthibernate1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        String name;

        @ManyToMany
        Set<Student> students = new HashSet<>();

    /**
     * Shows a many to one relationship, that is the most efficient mapping
     * as it closest matches the relational DB FK mapping.
     * The Mapping is controlled from the child end, in this case, this
     * class Course, maps to School.
     * Course knows about school, but school doesn't know about its courses.
     */
    @ManyToOne
        School school;

        public Course() {
        }

        public Course(String name) {
            this.name = name;
        }
        public void addStudent(Student student){
            students.add(student);
        }

    /**
     * @param school the school being set
     * @return reference to this
     **/
    public Course setSchool(School school) {
        this.school = school;
        return this;
    }

    /**
     * @return school
     **/
    public School getSchool() {
        return school;
    }
}

@Repository
interface CourseRepository extends CrudRepository<Course, Long> {
    Course findByNameIgnoreCase(@Param("name") String name);

    List<Course> findBySchool(@Param("school") School school);
}

    @Entity
    class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        String name;

        @ManyToMany
        Set<com.nbh.springtesthibernate1.Course> courses = new HashSet<>();

        public Student() {
        }

        public Student(String name) {
            this.name = name;
        }

        public void addCourse(com.nbh.springtesthibernate1.Course course){
            courses.add(course);
        }
}


@Repository
interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByName(@Param("name")  String name);
}
