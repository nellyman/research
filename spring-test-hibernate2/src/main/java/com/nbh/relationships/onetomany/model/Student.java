package com.nbh.relationships.onetomany.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        String name;

        @ManyToMany
        Set<Course> courses = new HashSet<>();

        public Student() {
        }

        public Student(String name) {
            this.name = name;
        }

        public void addCourse(Course course){
            courses.add(course);
        }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * @return courses
     **/
    public Set<Course> getCourses() {
        return courses;
    }
}
