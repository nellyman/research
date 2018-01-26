package com.cisco.asf.dataadvisor.category.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataTypeSearchServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataTypeSearchServiceImpl.class);

    private static final String NAME_FIELD="name";
    private static final String DESCRIPTION_FIELD="description";
    private static final String KEYWORDS = "keywords";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<DataType> search(String searchTerm) throws IOException {
        if (searchTerm==null || "".equals(searchTerm)){
            return new ArrayList<DataType>(0);
        }
        String text = searchTerm.trim().toLowerCase();
        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        final QueryBuilder queryBuilder = fullTextEntityManager.
                getSearchFactory().
                buildQueryBuilder().
                forEntity(DataType.class).
                get();

        Query luceneQuery;
        if (text.contains("*") || text.contains("?")){
            LOGGER.debug("Searching text '{}'  using wildcards... ", text);
            luceneQuery = queryBuilder
                    .keyword()
                    .wildcard()
                    .onFields(NAME_FIELD,KEYWORDS, DESCRIPTION_FIELD)
                    .matching(text)
                    .createQuery();
        }else if (text.startsWith("\"") && text.endsWith("\"")){
            // lets remove the quotes...
            text= text.replaceAll("\"","");
            if (text==null || "".equals(text)){
                return new ArrayList<>(0);
            }
            LOGGER.debug("Searching text '{}' using Phrase search... ", text);
            // guessing we have a sentence entered.
            Query luceneQueryName = queryBuilder
                    .phrase()
                    .onField(NAME_FIELD)
                    .sentence(text)
                    .createQuery();

            Query luceneQueryDescription = queryBuilder
                    .phrase()
                    .onField(DESCRIPTION_FIELD)
                    .sentence(text)
                    .createQuery();

            Query luceneQueryKeywords = queryBuilder
                    .phrase()
                    .onField(KEYWORDS)
                    .sentence(text)
                    .createQuery();

            luceneQuery=queryBuilder
                    .bool()
                    .should(luceneQueryName)
                    .should(luceneQueryDescription)
                    .should(luceneQueryKeywords)
                    .createQuery();
        }else{
            LOGGER.debug("Searching text '{}' using standard search... ", text);
            luceneQuery = queryBuilder
                    .keyword()
                    .fuzzy()
                    .withPrefixLength(4)
                    .withEditDistanceUpTo(1)
                    .onFields(NAME_FIELD,KEYWORDS, DESCRIPTION_FIELD)
                    .matching(text)
                    .createQuery();
        }

        List<DataType> results =  fullTextEntityManager.
                createFullTextQuery(luceneQuery, DataType.class).
                getResultList();

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("Found {} result(s)", results.size());
        }
        return results;
    }
}
