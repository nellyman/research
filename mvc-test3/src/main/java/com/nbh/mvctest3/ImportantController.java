package com.nbh.mvctest3;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/important")
public class ImportantController {

    @GetMapping(path = "/stuff")
    public ResponseEntity<String> importantInformation(){
        return ResponseEntity.ok("important");
    }


}
