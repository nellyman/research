package com.nbh.springtestsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    public Iterable<Student> listStudents(){
        return studentRepo.findAll();
    }

    @GetMapping(params = {"id"})
    public Student getOne(@PathVariable Long id){
        return studentRepo.findOne(id);
    }

    @DeleteMapping(params = {"id"})
    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id){
        studentRepo.delete(id);
        return ResponseEntity.ok().body("OK");
    }
}
