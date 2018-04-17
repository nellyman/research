package com.nbh.relationships.onetomany;


import com.nbh.relationships.onetomany.model.Course;
import com.nbh.relationships.onetomany.model.School;
import com.nbh.relationships.onetomany.repos.CourseRepository;
import com.nbh.relationships.onetomany.repos.SchoolRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ManyToOneRelationshipTest {

    Logger logger = LoggerFactory.getLogger(ManyToOneRelationshipTest.class);

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SchoolRepo schoolRepo;

    /**
     *
     */
    @Test
    public void shouldShowManyToOneRelationship(){
        logger.info("**********");
        School school = new School("school1");
        schoolRepo.save(school);
        logger.info("School saved");

        Course course = new Course("Maths");
        course = courseRepository.save(course);
        course.setSchool(school);

        entityManager.persistAndFlush(course);
        logger.info("School-course saved");

        logger.info("Looking for Courses at the school...");
        List<Course> courses = courseRepository.findBySchool(school);
        for (Course courseAtSchool : courses){
            logger.info(courseAtSchool.getName());
        }
        logger.info("Clear out the school");
        course.setSchool(null);
        entityManager.persistAndFlush(course);
        logger.info("**********");
    }

}
