package com.cisco.asf.dataadvisor.category.controller;

import com.cisco.asf.dataadvisor.category.model.DataType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@TestPropertySource(properties = "default.admin= bob")
public class DataTypeNotInWizardIT extends RestTemplateSupport{

    @Autowired
    private AllRoleAdminService adminService;

    private String parentCategoryUri;
    private String categoryUri;
    private Classification publicClassification;
    private DataType dataType;

    @Before
    public void setup(){
        adminService.setPowerAdmin(true);

        categoryUri = super.postCategory(super.getSafeName("category"));
        parentCategoryUri = super.postCategory(super.getSafeName("parent"));

        String publicName = super.getSafeName("public");
        Long publicId= super.postClassification(publicName);
        publicClassification = new Classification(publicId, publicName);

        String dataTypeName = super.getSafeName("dataType");
        dataType = new DataType(dataTypeName, publicClassification, "moon");
    }

    @After
    public void clear(){
        super.restTemplate.exchange(
                super.getLocalHostURL(categoryUri),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                String.class);

        super.restTemplate.exchange(
                super.getLocalHostURL(parentCategoryUri),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                String.class);
    }

    /**
     * The Category is left alone, so is in the Wizard by default!
     * @throws Exception
     */
    @Test
    public void shouldSearchDataTypeAsCategoryInWizard() throws Exception{

        //Add a DataType to the Category...
        this.addDataTypeToCategory();

        // now in the index?!
        // lets search and see if we can find it...

        List<DataType> types = this.performSearchForDataType();
        // should find it!!
        assertThat(types.size(), is(1));
    }

    @Test
    public void shouldNotSearchDataTypeAsNotInWizard() throws Exception{

        // update the Category, set as childCategory...
        ResponseEntity<Category> categoryResponse = super.restTemplate.getForEntity(
                super.getLocalHostURL(categoryUri),
                Category.class);

        assertThat(categoryResponse.getStatusCode(), is(HttpStatus.OK));

        Category category = categoryResponse.getBody();
        category.setHideFromWizard(true);
        category.setParentId(super.getIdFromLocationURI(parentCategoryUri));

        // update the Category so it's hidden from the Wizard
        ResponseEntity<String> response= super.restTemplate.exchange(
                super.getLocalHostURL(categoryUri),
                HttpMethod.PUT,
                new HttpEntity<Category>(category),
                String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        this.addDataTypeToCategory();

        // So the search now should not find the Category...
        List<DataType> types = this.performSearchForDataType();
        // should not find it!!
        assertThat("Expect that there is no results found as the Category is not in the Wizard(!)", types.size(), is(0));
    }


    /**
     * Assign a DataType to the Category, (in wizard).
     * Search find the result.
     * Change the Category to be hidden from the Wizard.
     * Search, should NOT find the DataType now...
     *
     * @throws Exception
     */
    @Test
    public void shouldHandleChangeOfCategoryFromInWizardToNotIn() throws Exception{

        this.addDataTypeToCategory();

        ResponseEntity<String> response = super.restTemplate.exchange(
                super.getLocalHostURL(categoryUri+"/types"),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);

        // So the search now should not find the Category...
        List<DataType> types = this.performSearchForDataType();
        // should find it!!
        assertThat(
                "Category should be in Wizard, the DataType should be visible.",
                types.size(),
                is(1));

        // update the Category, set as childCategory...
        ResponseEntity<String> categoryResponse = super.restTemplate.getForEntity(
                super.getLocalHostURL(categoryUri),
                String.class);

        // update the Category so it's hidden from the Wizard
        Category category = super.getCategoryFromJson(categoryResponse);
        category.setHideFromWizard(true);
        category.setParentId(super.getIdFromLocationURI(parentCategoryUri));
        category.setId(super.getIdFromLocationURI(categoryUri));

        DataType dt = category.getTypes().iterator().next();

        Set<DataType> typesToReturn= new HashSet<>(1);
        typesToReturn.add(new DataType(dt.getId(), dt.getName()));
        category.setTypes(typesToReturn);

        response= super.restTemplate.exchange(
                super.getLocalHostURL(categoryUri),
                HttpMethod.PUT,
                new HttpEntity<Category>(category),
                String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        response = super.restTemplate.exchange(
                super.getLocalHostURL(categoryUri+"/types"),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);

        types = this.performSearchForDataType();
        // should not find it!!
        assertThat(
                "Expect that there is no results found as the Category has been set hideFromWizard as true",
                types.size(),
                is(0));

        // lets get the DataType and examine it...
        response = super.restTemplate.exchange(
                super.getLocalHostURL(categoryUri+"/types"),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        System.out.print("wait");
    }


    private void addDataTypeToCategory(){
        String uri= super.getLocalHostURL(categoryUri+"/types");
        ResponseEntity<String> response = super.restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<DataType>(dataType),
                String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }


    private List<DataType> performSearchForDataType()throws Exception{
        ResponseEntity<String> searchResponse = super.restTemplate.getForEntity(
                super.getLocalHostURL("/api/filter?name=MOON"),
                String.class);

        assertThat(searchResponse.getStatusCode(), is(HttpStatus.OK));
        return super.getDataTypesFromJsonSearchResponse(searchResponse);
    }
}
