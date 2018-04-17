package com.nbh.relationships.onetomany;

import com.nbh.relationships.onetomany.model.Course;
import com.nbh.relationships.onetomany.model.Student;
import com.nbh.relationships.onetomany.repos.CourseRepository;
import com.nbh.relationships.onetomany.repos.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HibernateFirstTest {

    Logger logger = LoggerFactory.getLogger(HibernateFirstTest.class);

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Before
    public void setup(){
        Student bob= entityManager.persistAndFlush(new Student("Bob"));
        Student phil= entityManager.persistAndFlush(new Student("Phil"));
        Student richard = entityManager.persistAndFlush(new Student("Richard"));

        Course geography = entityManager.persistAndFlush(new Course("Geography"));
        Course maths = entityManager.persistAndFlush(new Course("Maths"));
    }

    @Test
    public void registerAllStudentsOnMaths(){
        logger.info("***********");
        logger.info("start tests...");
        logger.info("Get Course");
        Course maths = courseRepository.findByNameIgnoreCase("maths");
        assertThat( maths).isNotNull().withFailMessage("Expect Maths course to be found");
        logger.info("Get Students");
        Iterable<Student> students = studentRepository.findAll();
        for (Student student: students){
            maths.addStudent(student);
            //student.addCourse(maths);
        }
        entityManager.flush();
        assertThat(maths.getStudents().size()).isEqualTo(3);
        Course maths1 = courseRepository.findByNameIgnoreCase("maths");
        logger.info("*  "+maths1.getStudents().iterator().next().getName());
        logger.info("tests complete");
        logger.info("***********");
    }
}