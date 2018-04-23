package com.nbh.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class SampleBookInitializer implements ApplicationRunner {

    private final BookRepo bookRepo;

    public SampleBookInitializer(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.bookRepo
                .deleteAll()
                .thenMany(
                        Flux.just(
                                "Cloud Native Java|jlong",
                                "Spring Security 3.1|rwinch",
                                "Spring in Action|cwalls"))
                .map(t -> t.split("\\|"))
                .map(tuple -> new Book(null, tuple[0], tuple[1]))
                .flatMap(this.bookRepo::save)
                .thenMany(this.bookRepo.findAll())
                .subscribe(book -> log.info(book.toString()));
    }
}
