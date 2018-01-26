package com.nbh.spring.testing.datajpa;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * http://www.baeldung.com/spring-boot-testing
 *
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test
 * features and JUnit. Whenever we are using any Spring Boot testing features in out
 * JUnit tests, this annotation will be required.
 *
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 * configuring H2, an in-memory database
 * setting Hibernate, Spring Data, and the DataSource
 * performing an @EntityScan
 * turning on SQL logging
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    /**
     * To carry out some DB operation, we need some records already setup in our database.
     * To setup such data, we can use TestEntityManager. The TestEntityManager provided
     * by Spring Boot is an alternative to the standard JPA EntityManager that provides
     * methods commonly used when writing tests.
     */
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee alex = new Employee("alex");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        Employee found = employeeRepository.findByName(alex.getName());

        // then
        assertThat(found.getName()).isEqualTo(alex.getName());
    }

}