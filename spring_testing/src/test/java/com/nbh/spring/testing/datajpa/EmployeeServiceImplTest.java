package com.nbh.spring.testing.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * http://www.baeldung.com/spring-boot-testing
 * Ideally, we should be able to write and test our Service layer code without wiring in our full persistence layer.
 *
 */
@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    /**
     * To check the Service class, we need to have an instance of Service class
     * created and available as a @Bean so that we can @Autowire it in our
     * test class. This configuration is achieved by using the @TestConfiguration annotation.
    *
     * During component scanning, we might find components or configurations created
     * only for specific tests accidentally get picked up everywhere. To help prevent that,
     * Spring Boot provides @TestConfiguration annotation that can be used on classes in
     * src/test/java to indicate that they should not be picked up by scanning.
     */
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        Employee alex = new Employee("alex");

        Mockito.when(employeeRepository.findByName(alex.getName()))
                .thenReturn(alex);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);

        Assertions.assertThat(found.getName())
                .isEqualTo(name);
    }

}
