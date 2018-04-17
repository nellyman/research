package com.nbh.springtesthibernate3;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestRepoTest {

    @Autowired
    FishRepo fishRepo;

    @Value("${local.server.port}")
    protected int runningTestPort;

    TestRestTemplate restTemplate = new TestRestTemplate();

    String getLocalHostURL(String path){
        return "http://127.0.0.1:" + runningTestPort + path;
    }

    @Before
    public void setup(){
        fishRepo.save(new Fish("cod"));
        fishRepo.save(new Fish("shark"));
    }

    @Test
    public void shouldGetFishFromFrontEnd() throws Exception{


        ResponseEntity<List> fishes = restTemplate.exchange(
                getLocalHostURL("/api/fish"),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                List.class);


        //Assertions.assertThat()
    }

}