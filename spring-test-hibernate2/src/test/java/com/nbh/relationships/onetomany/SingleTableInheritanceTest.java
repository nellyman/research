package com.nbh.relationships.onetomany;

import com.nbh.relationships.onetomany.model.Person;
import com.nbh.relationships.onetomany.model.Pupil;
import com.nbh.relationships.onetomany.model.Teacher;
import com.nbh.relationships.onetomany.repos.PersonRepo;
import com.nbh.relationships.onetomany.repos.PupilRepo;
import com.nbh.relationships.onetomany.repos.TeacherRepo;
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
public class SingleTableInheritanceTest {

    Logger logger = LoggerFactory.getLogger(SingleTableInheritanceTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PupilRepo pupilRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private PersonRepo personRepo;

    @Test
    public void shouldSetupData(){
        logger.info("************");
        Pupil tom = new Pupil("Tom");
        pupilRepo.save(tom);
        Teacher smith = new Teacher("MrsSmith");
        teacherRepo.save(smith);
        Person malic = new Person("malic");
        personRepo.save(malic);
        logger.info("************");

        List<Person> entries = entityManager.getEntityManager().createQuery("select p from Person p").getResultList();

        // note: for following the age is all the same, 20, as they are Person objects, and age is stored
        // as an instance variable with person...
        for (Person person : entries){
            logger.info("** "+person.getName()+" "+person.whoAmI()+"  age:"+person.getAge());
        }

        logger.info("************");

        //lets try some Java Inheritance, see what happens...
        Person bob = new Teacher("bob");
        bob = personRepo.save(bob);

        logger.info(bob.whoAmI()+" age:"+bob.getAge());
        /*
        Saves as-
        Hibernate: insert into person (id, name, dtype) values (null, ?, 'Teacher')
         binding parameter [1] as [VARCHAR] - [bob]
         */
    }
}