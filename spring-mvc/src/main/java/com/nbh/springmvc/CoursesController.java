package com.nbh.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping(path = "/courses")
public class CoursesController {

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping
    public ResponseEntity<Iterable<Course>> getCourses() {
        return ResponseEntity.ok().body(courseRepo.findAll());
    }

    @PostMapping(consumes = "Application/json")
    public ResponseEntity<String> saveCourse(@RequestBody Course course) {
        Course persisted = courseRepo.save(course);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(persisted.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Course> getCourse(@PathVariable("name")String name) {
        Course persisted = courseRepo.findByName(name);
        if (persisted==null){
            return ResponseEntity
                    .noContent()
                    .build();
        }
        return ResponseEntity
                .ok(persisted);
    }
}
