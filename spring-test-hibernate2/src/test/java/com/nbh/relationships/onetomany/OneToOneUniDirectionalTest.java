package com.nbh.relationships.onetomany;

import com.nbh.relationships.onetomany.model.Country;
import com.nbh.relationships.onetomany.model.Flag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OneToOneUniDirectionalTest {

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void createAndDeleteOfOneToOne(){

        Country country = entityManager.persistAndFlush(new Country("UK"));

        Flag jack = entityManager.persistAndFlush(new Flag("flag"));
        country.setFlag(jack);
        entityManager.persistAndFlush(country);

        /**
         * The above performs-
         * Hibernate: insert into country (id, flag_id, name) values (null, ?, ?)
         binding parameter [1] as [BIGINT] - [null]
         binding parameter [2] as [VARCHAR] - [UK]

         Hibernate: insert into flag (id, name) values (null, ?)
         binding parameter [1] as [VARCHAR] - [flag]

         Hibernate: update country set flag_id=?, name=? where id=?
         binding parameter [1] as [BIGINT] - [1]
         binding parameter [2] as [VARCHAR] - [UK]
         binding parameter [3] as [BIGINT] - [1]
         */

        // lets do a select....
        entityManager.clear();

        Country country2 = entityManager.find(Country.class, country.getId());

        /**
         * Which results in this-
         *
         * Hibernate: select country0_.id as id1_1_0_, country0_.flag_id as flag_id3_1_0_, country0_.name as name2_1_0_,
         * flag1_.id as id1_6_1_, flag1_.name as name2_6_1_, monarch2_.id as id1_9_2_, monarch2_.country_id as country_3_9_2_,
         * monarch2_.name as name2_9_2_
         *
         * from country country0_
         *
         * left outer join flag flag1_
         * on country0_.flag_id=flag1_.id
         *
         * left outer join monarch monarch2_
         * on country0_.id=monarch2_.country_id
         *
         * where country0_.id=?
         *
         binding parameter [1] as [BIGINT] - [1]

         extracted value ([id1_6_1_] : [BIGINT]) - [1]
         extracted value ([id1_9_2_] : [BIGINT]) - [null]
         extracted value ([flag_id3_1_0_] : [BIGINT]) - [1]
         extracted value ([name2_1_0_] : [VARCHAR]) - [UK]
         extracted value ([name2_6_1_] : [VARCHAR]) - [flag]
         */

        /**
         * A delete-
         * Hibernate: update country set flag_id=?, name=? where id=?
         binding parameter [1] as [BIGINT] - [null]
         binding parameter [2] as [VARCHAR] - [UK]
         binding parameter [3] as [BIGINT] - [1]
         */
    }

}