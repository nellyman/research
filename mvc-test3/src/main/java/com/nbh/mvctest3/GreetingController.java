package com.nbh.mvctest3;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/greetings")
public class GreetingController {


    @GetMapping
    public ResponseEntity<String> echo(){
        return ok("Hello");
    }

    @GetMapping(path="{name}")
    public ResponseEntity<String> hello(@PathVariable("name") String name){

        return ok("Hello "+name);
    }
}
