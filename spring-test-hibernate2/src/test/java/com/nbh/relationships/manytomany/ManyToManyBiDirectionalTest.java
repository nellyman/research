package com.nbh.relationships.manytomany;

import com.nbh.relationships.manytomany.model.Country;
import com.nbh.relationships.manytomany.model.River;
import com.nbh.relationships.manytomany.repos.RiverRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ManyToManyBiDirectionalTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private RiverRepo riverRepo;

    @Test
    public void shouldMapManyToManyBiDirectional(){

        Country country = new Country("DR Congo");
        country.addRiver(new River("Congo"));
        country.addRiver(new River("LittleCongo"));

        entityManager.persistAndFlush(country);
        /**
         * Hibernate: insert into country (id, name) values (null, ?)
         binding parameter [1] as [VARCHAR] - [DR Congo]
         Hibernate: insert into river (id, name) values (null, ?)
         binding parameter [1] as [VARCHAR] - [Congo]
         Hibernate: insert into river (id, name) values (null, ?)
         binding parameter [1] as [VARCHAR] - [LittleCongo]
         Hibernate: insert into countries_to_rivers (country_id, rivers_id) values (?, ?)
         binding parameter [1] as [BIGINT] - [1]
         binding parameter [2] as [BIGINT] - [1]
         Hibernate: insert into countries_to_rivers (country_id, rivers_id) values (?, ?)
         binding parameter [1] as [BIGINT] - [1]
         binding parameter [2] as [BIGINT] - [2]
         */

        // and the delete...
        River congo=riverRepo.findByName("Congo");

        /**
         * Hibernate: select river0_.id as id1_4_, river0_.name as name2_4_ from river river0_ where river0_.name=?
         binding parameter [1] as [VARCHAR] - [Congo]
         extracted value ([id1_4_] : [BIGINT]) - [1]
         */

        country.removeRiver(congo);

        // again whole collection is removed and then recreated...
        /**
         * Hibernate: delete from countries_to_rivers where country_id=?
         binding parameter [1] as [BIGINT] - [1]
         Hibernate: insert into countries_to_rivers (country_id, rivers_id) values (?, ?)
         binding parameter [1] as [BIGINT] - [1]
         binding parameter [2] as [BIGINT] - [2]
         */

        entityManager.persistAndFlush(country);
    }
}
