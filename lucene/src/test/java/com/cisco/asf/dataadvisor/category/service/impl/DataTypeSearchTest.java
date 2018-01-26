package com.cisco.asf.dataadvisor.category.service.impl;

import com.cisco.asf.dataadvisor.category.model.DataType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Need to create index on startup, modify on change, and search correctly (!)
 *
 * Created by nhardwic on 09/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApiApplication.class)
@ActiveProfiles("test")
@Transactional
public class DataTypeSearchTest {

    @Autowired
    private CustomerCategoryInjector customerCategoryInjector;

    @Autowired
    private ClassificationInjector classificationInjector;

    @Autowired
    private SimpleSearchService<DataType> simpleSearchService;

    @Autowired
    private SearchIndexService<DataType> searchIndexService;

    @Autowired
    private DataTypeRepository dataTypeRepository;

    @Before
    public void setUp(){
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
        SecurityContextHolder.setContext(new TestSecurityContext("bob"));
        //  customerCategoryInjector.setup();
        classificationInjector.setup();
    }

    @Test
    public void shouldReturnNoResultsWithEmptySearch() throws Exception{
        String retention = "perm";
        String dataTypeName = "Customer Data";
        DataType dataType = this.saveDataType(dataTypeName,null,null, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("");
        assertThat(results.size(), is(0));
        results = simpleSearchService.search(null);
        assertThat(results.size(), is(0));
    }


    @Test
    public void shouldPerformSimpleSearchOnName()throws Exception{
        String dataTypeName = "Customer Data";
        String retention = "perm";
        DataType dataType = this.saveDataType(dataTypeName,null,null, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("customer");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));

        results = simpleSearchService.search("CUSTOMER");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        dataTypeRepository.delete(dataType);
    }

    @Test
    public void shouldPerformSimpleSearchOnDescription()throws Exception{
        String retention = "perm";
        String dataTypeName = "Customer Data";
        String description="Fried Eggs";
        DataType dataType = this.saveDataType(dataTypeName,description,null, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("eggs");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));
        dataTypeRepository.delete(dataType);
    }

    @Test
    public void shouldPerformSimpleSearchOnKeywords()throws Exception{
        String retention = "perm";
        String dataTypeName = "Customer Data";
        String keywords="sun, moon, planets";
        DataType dataType = this.saveDataType(dataTypeName,null,keywords, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("moon");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));
        dataTypeRepository.delete(dataType);
    }

    @Test
    public void shouldPerformSearchOnKeywordsWithMultipleWords()throws Exception{
        String retention = "perm";
        String dataTypeName = "Customer Data";
        String keywords1="sun, this is the solar system";
        DataType dataType1 = this.saveDataType(dataTypeName,null,keywords1, retention);
        searchIndexService.updateIndex(dataType1);

        String keywords2="moon, this is not the solar system";
        DataType dataType2 = this.saveDataType(dataTypeName,null,keywords2, retention);
        searchIndexService.updateIndex(dataType2);


        // lets search..
        List<DataType> results = simpleSearchService.search("\"this is the solar system\"");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));

        // partial match...
        results = simpleSearchService.search("\"the solar system\"");
        assertThat(results.size(), is(2));

        //less words...
        results = simpleSearchService.search("\"solar system\"");
        assertThat(results.size(), is(2));

        //reversed
        results = simpleSearchService.search("\"system solar\"");
        // no words match..
        assertThat(results.size(), is(0));

        //reversed, no quotes
        results = simpleSearchService.search("system solar");
        // both match..
        assertThat(results.size(), is(2));

        //single words...
        results = simpleSearchService.search("\"system\"");
        assertThat(results.size(), is(2));

        // partial matches...
        results = simpleSearchService.search("\"is the\"");
        assertThat(results.size(), is(1));

        results = simpleSearchService.search("is the");
        assertThat(results.size(), is(2));

        results = simpleSearchService.search("\"moon sun\"");
        assertThat(results.size(), is(0));

        results = simpleSearchService.search("moon sun");
        assertThat(results.size(), is(2));

        results = simpleSearchService.search("\"sun solar\"");
        assertThat(results.size(), is(0));

        results = simpleSearchService.search("solar sun");
        assertThat(results.size(), is(2));

        dataTypeRepository.delete(dataType1);
        dataTypeRepository.delete(dataType2);
    }

    @Test
    public void shouldPerformSearchSwarupaCriteria1()throws Exception{
        String dataTypeName = "Customer Support";
        String keywords="sun, moon, planets";
        String description="Fried Eggs";
        String retention = "perm";

        DataType dataType = this.saveDataType(dataTypeName,description,keywords, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("customer support");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));

        results = simpleSearchService.search("support");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        dataTypeRepository.delete(dataType);
    }


    @Test
    //@Ignore("Require Stemming setup")
    public void shouldUseStemmingToFindQuartily()throws Exception {
        String dataTypeName = "Quarterly Business Review";
        String keywords = "QBRs";
        String description = "This is the description.";
        String retention = "perm";

        DataType dataType = this.saveDataType(dataTypeName, description, keywords, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("quarter");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));
        dataTypeRepository.delete(dataType);
    }

    @Test
    public void shouldUsePhraseToFindQBR() throws Exception{
        String dataTypeName = "Quarterly Business Review";
        String keywords = "QBRs";
        String description = "This is the description.";
        String retention = "perm";

        DataType type1 = this.saveDataType(dataTypeName, description, keywords, retention);
        searchIndexService.updateIndex(type1);

        // another similar sounding one, with different phrase
        DataType type2 = this.saveDataType("Yearly Business Review", "FYBRs", "nothing", retention);
        searchIndexService.updateIndex(type2);

        List<DataType> results = simpleSearchService.search("\"quarter business review\"");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));

        // search without quotes results in OR search, both entries returned.
        results = simpleSearchService.search("quarter business review");

        assertThat(results.size(), is(2));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));

        dataTypeRepository.delete(type1);
        dataTypeRepository.delete(type2);
    }

    @Test
    public void shouldUseMultipleEntriesToCreateAnORQuery() throws Exception{
        String dataTypeName = "Quarterly Business Review";
        String keywords = "QBRs";
        String description = "This is the description.";
        String retention = "perm";

        DataType type1 = this.saveDataType(dataTypeName, description, keywords, retention);
        searchIndexService.updateIndex(type1);

        DataType type2 = this.saveDataType("Max Shipping forecast", "forecasting for shipping", "sea casting, forecast, ships", retention);
        searchIndexService.updateIndex(type2);

        // we have one entry matched on review, the other on forecast...
        List<DataType> results = simpleSearchService.search("review forecast");
        assertThat(results.size(), is(2));

        // extra searching on keywords...
        results = simpleSearchService.search("sea casting");
        assertThat(results.size(), is(1));

        results = simpleSearchService.search("sea");
        assertThat(results.size(), is(1));

        dataTypeRepository.delete(type1);
        dataTypeRepository.delete(type2);
    }

    @Test
    public void shouldHandleBadEntryForPhraseSearch() throws Exception{
        String dataTypeName = "Quarterly Business Review";
        String keywords = "QBRs";
        String description = "This is the description.";
        String retention = "perm";

        DataType type1 = this.saveDataType(dataTypeName, description, keywords, retention);
        searchIndexService.updateIndex(type1);

        List<DataType> results = simpleSearchService.search("\"");
        assertThat(results.size(), is(0));

        // extra searching on keywords...
        results = simpleSearchService.search(null);
        assertThat(results.size(), is(0));

        results = simpleSearchService.search("");
        assertThat(results.size(), is(0));

        dataTypeRepository.delete(type1);
    }

    @Test
    public void shouldPerformSearchQBR()throws Exception{
        String dataTypeName = "Quarterly Business Review";
        String keywords="QBRs, qbr";
        String description="This is the description.";
        String retention = "perm";

        DataType dataType = this.saveDataType(dataTypeName,description,keywords, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("business");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));

        results = simpleSearchService.search("review");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));

        results = simpleSearchService.search("QUARTERLY");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        assertThat(results.get(0).getRetention(), is(retention));

        results = simpleSearchService.search("quarterly business review");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));

        results = simpleSearchService.search("QBR");

        assertThat(results.size(), is(1));
        assertThat(results.get(0).getName(), is(dataTypeName));
        dataTypeRepository.delete(dataType);
    }


    @Test
    public void shouldCheckThatNoResultsReturned()throws Exception{
        String dataTypeName = "Customer Support";
        String keywords="sun, moon, planets";
        String description="Fried Eggs";
        String retention = "perm";

        DataType dataType = this.saveDataType(dataTypeName,description,keywords, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("steam");

        assertThat(results.size(), is(0));
        dataTypeRepository.delete(dataType);
    }

    @Test
    public void shouldCheckThatDataTypesNotInWizardAreNOTReturned()throws Exception{
        String dataTypeName = "Customer Support";
        String keywords="sun, moon, planets";
        String description="Fried Eggs";
        String retention = "perm";

        DataType dataType = this.saveDataType(dataTypeName,description,keywords, retention);
        searchIndexService.updateIndex(dataType);

        // lets search..
        List<DataType> results = simpleSearchService.search("steam");

        assertThat(results.size(), is(0));
        dataTypeRepository.delete(dataType);
    }


    private DataType saveDataType(String name, String description, String keywords, String retention){
        DataType dataType= new DataType(name, classificationInjector.highConfidential);
        dataType.setKeywords(keywords);
        dataType.setDescription(description);
        dataType.setRetention(retention);
        return dataTypeRepository.save(dataType);
    }
}