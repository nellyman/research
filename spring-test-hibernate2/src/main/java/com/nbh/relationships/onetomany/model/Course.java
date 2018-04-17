package com.nbh.relationships.onetomany.model;

import javax.persistence.*;
import java.util.HashSet;
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

    /**
     * @return students
     **/
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }
}


