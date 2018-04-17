package com.nbh.relationships.manytomany;

import com.nbh.relationships.manytomany.model.Component;
import com.nbh.relationships.manytomany.model.Supplier;
import com.nbh.relationships.manytomany.repos.ComponentRepo;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ManyToManyUniDirectionalTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ComponentRepo componentRepo;

    @Test
    public void shouldMapManyToMany(){

        Component c1 = new Component("Component1");

        Component c2 = new Component("Widgit");

        Supplier lucas = new Supplier("lucas");

        Supplier lister = new Supplier("Lister");

        c1.getSuppliers().add(lucas);
        c1.getSuppliers().add(lister);

        c2.getSuppliers().add(lister);
        c2.getSuppliers().add(lucas);

        entityManager.persist(c1);
        entityManager.persistAndFlush(c2);
        entityManager.clear();

/**Hibernate: insert into component (id, name) values (null, ?)
 binding parameter [1] as [VARCHAR] - [Component1]
 Hibernate: insert into supplier (id, name) values (null, ?)
 binding parameter [1] as [VARCHAR] - [lucas]
 Hibernate: insert into supplier (id, name) values (null, ?)
 binding parameter [1] as [VARCHAR] - [Lister]
 Hibernate: insert into component (id, name) values (null, ?)
 binding parameter [1] as [VARCHAR] - [Widgit]
 Hibernate: insert into component_supplier (supplier_id, component_id) values (?, ?)
 binding parameter [1] as [BIGINT] - [1]
 binding parameter [2] as [BIGINT] - [1]
 Hibernate: insert into component_supplier (supplier_id, component_id) values (?, ?)
 binding parameter [1] as [BIGINT] - [1]
 binding parameter [2] as [BIGINT] - [2]
 Hibernate: insert into component_supplier (supplier_id, component_id) values (?, ?)
 binding parameter [1] as [BIGINT] - [2]
 binding parameter [2] as [BIGINT] - [2]
 Hibernate: insert into component_supplier (supplier_id, component_id) values (?, ?)
 binding parameter [1] as [BIGINT] - [2]
 binding parameter [2] as [BIGINT] - [1]
 Hibernate: select component0_.id as id1_0_0_, component0_.name as name2_0_0_ from component component0_ where component0_.id=?
 binding parameter [1] as [BIGINT] - [1]
 extracted value ([name2_0_0_] : [VARCHAR]) - [Component1]
 Created collection wrapper: [com.nbh.manytomany.Component.suppliers#1]
*/


        Component persisted  = componentRepo.findOne(c1.getId());

        MatcherAssert.assertThat(persisted.getSuppliers().size(), CoreMatchers.is(2));
        /**
Hibernate: select suppliers0_.supplier_id as supplier1_1_0_, suppliers0_.component_id as componen2_1_0_, supplier1_.id as id1_2_1_,
         supplier1_.name as name2_2_1_
         from component_supplier suppliers0_ inner join supplier supplier1_ on suppliers0_.component_id=supplier1_.id
         where suppliers0_.supplier_id=?
binding parameter [1] as [BIGINT] - [1]
extracted value ([id1_2_1_] : [BIGINT]) - [1]
extracted value ([name2_2_1_] : [VARCHAR]) - [lucas]
extracted value ([supplier1_1_0_] : [BIGINT]) - [1]
extracted value ([componen2_1_0_] : [BIGINT]) - [1]
extracted value ([id1_2_1_] : [BIGINT]) - [2]
extracted value ([name2_2_1_] : [VARCHAR]) - [Lister]
extracted value ([supplier1_1_0_] : [BIGINT]) - [1]
extracted value ([componen2_1_0_] : [BIGINT]) - [2]*/

        // lets now delete an entry off the Suppliers...

        persisted.getSuppliers().remove(0);

        entityManager.persistAndFlush(persisted);
        entityManager.clear();

        /**
         * DELETES OFF EVERYTHING AS IN UNIDIRECTIONAL ONE TO MANY
         *
         * Hibernate: delete from component_supplier where supplier_id=?
         binding parameter [1] as [BIGINT] - [1]

         THEN RECREATES. THE MAPPING..
         Hibernate: insert into component_supplier (supplier_id, component_id) values (?, ?)
         binding parameter [1] as [BIGINT] - [1]
         binding parameter [2] as [BIGINT] - [2]*/
    }

}