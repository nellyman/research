package com.nbh.springresttesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Service
class LoadStudentService{

    private EmployeeRepo employeeRepo;

    @Autowired
    public LoadStudentService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
        employeeRepo.save(new Employee("Bob"));
        employeeRepo.save(new Employee("Jeff"));
        employeeRepo.save(new Employee("Sam"));
    }
}

@Entity
class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    /**
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }

}


@RepositoryRestResource
interface EmployeeRepo extends CrudRepository<Employee, Long>{

}