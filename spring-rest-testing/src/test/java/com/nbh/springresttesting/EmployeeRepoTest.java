package com.nbh.springresttesting;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldGetDetails(){
        String bob = "bob";
        Employee employee = new Employee(bob);
        employee = entityManager.persistAndFlush(employee);
        Employee persistedEmp = employeeRepo.findOne(employee.getId());
        Assertions.assertThat(persistedEmp).isSameAs(employee);
    }
}