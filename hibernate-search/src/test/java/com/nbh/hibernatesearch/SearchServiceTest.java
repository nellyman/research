package com.nbh.hibernatesearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

    @Autowired
    SearchService searchService;

    @Test
    public void shouldFindMini(){

        List<Manufacturer> manufacturers = searchService.search("mini");
        assertThat(manufacturers.size()).isEqualTo(1);
    }
}