package com.nbh.hibernatesearch;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@Service
public class SearchService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public List<Manufacturer> search(String query){

        FullTextEntityManager mgr = getFullTextEntityManager(this.entityManager);

        final QueryBuilder qb = mgr.getSearchFactory()
                .buildQueryBuilder().forEntity(Manufacturer.class).get();

        final FullTextQuery jpaQuery = this.createFullTextQuery(qb, query);

        final List<Manufacturer> results = jpaQuery.getResultList();
        final int resultCount=jpaQuery.getResultSize();
        //final List<Facet> facets = facetManager.getFacets(FUNCTION_FACET_NAME);
        return results;
    }


    private FullTextQuery createFullTextQuery(final QueryBuilder qb, final String actualQuery) {

        Query luceneQuery;

        final String[] fields = new String[] {"name","country"};

            luceneQuery = qb
                    .keyword()
                    .wildcard()
                    .onFields(fields)
                    .matching(actualQuery)
                    .createQuery();


        return getFullTextEntityManager(this.entityManager)
                .createFullTextQuery(luceneQuery, Manufacturer.class);
    }
}
