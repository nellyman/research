package com.nbh.springmvc2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HelloWorldController {


    @GetMapping(path="/hello")
    public ResponseEntity<String> getGreeting(){
        return new ResponseEntity("Hello", HttpStatus.OK);
    }

    @PostMapping(path="/upload")
    public ResponseEntity<String> loadGreeting(@RequestBody Payload payload){
        System.out.println("loaded: "+payload.getName());
        return ResponseEntity.ok("ok");
    }
}
