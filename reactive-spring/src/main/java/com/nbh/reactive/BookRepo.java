package com.nbh.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BookRepo extends ReactiveMongoRepository {

    Flux findBuAuthor(String author);

}
