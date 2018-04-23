package com.nbh.relationships.onetomany;

import com.nbh.relationships.onetomany.model.Janitor;
import com.nbh.relationships.onetomany.model.School;
import com.nbh.relationships.onetomany.repos.JanitorRepo;
import com.nbh.relationships.onetomany.repos.SchoolRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OneToManyBiDirectionalTest {


    Logger logger = LoggerFactory.getLogger(OneToManyBiDirectionalTest.class);
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    SchoolRepo schoolRepo;

    @Autowired
    JanitorRepo janitorRepo;


    @Test
    public void showOneToManyBiDirectionRelationship(){

        logger.info("Save school..");
        School school = entityManager.persistAndFlush(new School("school1"));
        logger.info("Save Jeff.");
        Janitor jeff = entityManager.persistAndFlush(new Janitor("Jeff"));
        school.addJanitor(jeff);
        Janitor bob =entityManager.persistAndFlush(new Janitor("Bob"));
        school.addJanitor(bob);
        Janitor rachel =entityManager.persistAndFlush(new Janitor("Rachel"));
        school.addJanitor(rachel);
        logger.info("Put Janitors into school..");
        entityManager.persistAndFlush(school);

        // lets get the school and check out the janitors...

        logger.info("Lets see the janitors in the school...");
        entityManager.clear(); // detaches all objects...
        showJanitors();

        // remove bob from school...
        Janitor bob1 = janitorRepo.findByName("Bob");
        logger.info("remove bob");
        school.removeJanitor(bob1);
        /*Hibernate: update person set school_id=? where id=?
binding parameter [1] as [BIGINT] - [null]
binding parameter [2] as [BIGINT] - [2]*/
        entityManager.flush();

        entityManager.clear();
        showJanitors();

        // lastly, lets just delete a janitor...
        Janitor jeff1 = janitorRepo.findByName("Jeff");
        entityManager.remove(jeff1);
        /*Hibernate: delete from person where id=?
binding parameter [1] as [BIGINT] - [1]*/
        entityManager.flush(); // actually perform changes...
        entityManager.clear(); // clear down any cached entries..
        showJanitors(); // now shows just Rachel
    }

    private void showJanitors(){
        School persisted = schoolRepo.findByName("school1");
        for (Janitor janitor : persisted.getJanitors()){
            logger.info(janitor.getName());
        }
    }

}
