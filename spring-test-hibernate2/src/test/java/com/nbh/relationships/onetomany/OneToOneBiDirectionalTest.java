package com.nbh.relationships.onetomany;


import com.nbh.relationships.onetomany.model.Country;
import com.nbh.relationships.onetomany.model.Monarch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OneToOneBiDirectionalTest {

    @Autowired
    TestEntityManager entityManager;


    @Test
    public void shouldMapOneToOneBiDirectionalRelationship(){

        Country country = new Country("Japan");
        country = entityManager.persist(country);

        Monarch bob = new Monarch("King bob");
        entityManager.persist(bob);
        country.setMonarch(bob);

        entityManager.persistAndFlush(country);

        assertThat(country.getMonarch(), is(notNullValue()));
    }

    @Test
    public void seeSelectToMonarchTable(){

        Country country = new Country("Japan");
        country = entityManager.persistAndFlush(country);
        entityManager.clear();

        Country country2 = entityManager.find(Country.class, country.getId());
        System.out.println("country2 = " + country2);

        /**
        The above performs-
        * Hibernate: select country0_.id as id1_1_0_, country0_.flag_id as flag_id3_1_0_, country0_.name as name2_1_0_,
        * flag1_.id as id1_6_1_, flag1_.name as name2_6_1_,
        * monarch2_.id as id1_9_2_, monarch2_.country_id as country_3_9_2_, monarch2_.name as name2_9_2_
        * from country
        * country0_ left outer join flag
        * flag1_ on country0_.flag_id=flag1_.id
        * left outer join monarch monarch2_ on country0_.id=monarch2_.country_id
        * where country0_.id=?
        *
binding parameter [1] as [BIGINT] - [2]
extracted value ([id1_6_1_] : [BIGINT]) - [null]
extracted value ([id1_9_2_] : [BIGINT]) - [null]
extracted value ([flag_id3_1_0_] : [BIGINT]) - [null]
extracted value ([name2_1_0_] : [VARCHAR]) - [Japan]
*/
    }

}
