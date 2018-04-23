package com.nbh.mvctest3;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/data")
public class DataController {

    @GetMapping(path = "/view/{name}", params = {"age", "date"}, produces = "application/json")
    public ResponseEntity<Data> getData(
            @PathVariable("name") String name,
            @PathParam("age") String age,
            @PathParam("date")String date){

        return ResponseEntity.ok(new Data(name, age, date));
    }
}
