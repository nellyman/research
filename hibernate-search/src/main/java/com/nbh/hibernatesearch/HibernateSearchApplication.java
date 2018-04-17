package com.nbh.hibernatesearch;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@SpringBootApplication(scanBasePackages = {"com.nbh.hibernatesearch"})
public class HibernateSearchApplication {

    @Autowired
    EntityLoader entityLoader;

    public static void main(String[] args) {
        SpringApplication.run(HibernateSearchApplication.class, args);
    }

}

@Component
class EntityLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(EntityLoader.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Data");
        Country UK = countryRepo.save(new Country("UK"));
        Country Spain = countryRepo.save(new Country("Spain"));
        Country Germany = countryRepo.save(new Country("Germany"));
        Country USA = countryRepo.save(new Country("USA"));
        Country Japan = countryRepo.save(new Country("Japan"));
        countryRepo.save(new Country("Korea"));
        countryRepo.save(new Country("Italy"));

        manufacturerRepo.save(new Manufacturer("Aston Martin", UK));
        manufacturerRepo.save(new Manufacturer("Seat", Spain));
        manufacturerRepo.save(new Manufacturer("BMW", Germany));
        manufacturerRepo.save(new Manufacturer("Dodge", USA));
        manufacturerRepo.save(new Manufacturer("Honda", Japan));
        manufacturerRepo.save(new Manufacturer("Mini", UK));

        logger.info("Performing indexing....");

        FullTextEntityManager mgr = getFullTextEntityManager(this.entityManager);
        mgr.createIndexer().startAndWait();

        logger.info("Indexing complete!");
    }
}
