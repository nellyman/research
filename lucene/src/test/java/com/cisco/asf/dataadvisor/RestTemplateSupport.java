package com.cisco.asf.dataadvisor;

import com.cisco.asf.dataadvisor.category.model.DataType;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public abstract class RestTemplateSupport {

    private static int namePostFix=835;

    @Value("${local.server.port}")
    protected int runningTestPort;

    protected TestRestTemplate restTemplate = new TestRestTemplate();

    /**
     * Adds an incremental index to any supplied name
     * to prevent naming collisions.
     * @param name The name core.
     * @return The name plus an index on the end.
     */
    protected String getSafeName(String name){
        return name+ RestTemplateSupport.namePostFix++;
    }

    /**
     * returns a String using localhost and the running port with the path appended,
     * using a leading slash on the path.
     * @param path The path, with leading slash.
     * @return String of localhost value.
     */
    protected String getLocalHostURL(String path){
        return "http://127.0.0.1:" + runningTestPort + path;
    }

    /**
     * Gets the Tool and it's properties from the JSON
     * @param response The Response to recieve.
     * @return The Tool
     * @throws Exception if a problem reading the JSON occurs.
     */
    protected Tool getToolFromJson(ResponseEntity<String> response)throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody());
        Tool tool = mapper.readValue(rootNode.toString(), Tool.class);
        JsonNode dataNode = rootNode.findPath("_embedded").get("categories");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolCategory> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolCategory>>() {});
            tool.setCategories(data);
        }
        dataNode = rootNode.findPath("_embedded").get("security");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolSecurity> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolSecurity>>() {});
            tool.setSecurity(data);
        }
        dataNode = rootNode.findPath("_embedded").get("compliances");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolCompliance> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolCompliance>>() {});
            tool.setCompliances(data);
        }
        dataNode = rootNode.findPath("_embedded").get("details");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolDetails> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolDetails>>() {});
            tool.setDetails(data);
        }
        dataNode = rootNode.findPath("_embedded").get("support");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolSupport> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolSupport>>() {});
            tool.setSupport(data);
        }
        dataNode = rootNode.findPath("_embedded").get("images");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            List<ToolImage> data = mapper.readValue(dataNode.toString(), new TypeReference<List<ToolImage>>() {});
            tool.setImages(data);
        }
        dataNode = rootNode.findPath("_embedded").get("shelflife");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ShelfLife> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ShelfLife>>() {});
            tool.setShelflife(data);
        }
        dataNode = rootNode.findPath("_embedded").get("audiences");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<Audience> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<Audience>>() {});
            tool.setAudiences(data);
        }
        dataNode = rootNode.findPath("_embedded").get("classification");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Classification data = mapper.readValue(dataNode.toString(), new TypeReference<Classification>() {});
            tool.setClassification(data);
        }
        dataNode = rootNode.findPath("_embedded").get("collaboration");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<Collaboration> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<Collaboration>>() {});
            tool.setCollaboration(data);
        }
        dataNode = rootNode.findPath("_embedded").get("toolTypes");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolType> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolType>>() {});
            tool.setToolTypes(data);
        }
        dataNode = rootNode.findPath("_embedded").get("toolContent");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            Set<ToolContent> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<ToolContent>>() {});
            tool.setToolContent(data);
        }
        dataNode = rootNode.findPath("_embedded").get("toolVideo");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            ToolVideo data = mapper.readValue(dataNode.toString(), new TypeReference<ToolVideo>() {});
            tool.setToolVideo(data);
        }
        dataNode = rootNode.findPath("_embedded").get("toolMobile");
        if (dataNode!=null && !dataNode.isMissingNode()) {
            ToolMobile data = mapper.readValue(dataNode.toString(), new TypeReference<ToolMobile>() {});
            tool.setToolMobile(data);
        }
        return tool;
    }

    /**
     * Gets the Category+Types from a Category- /api/category/5
     * @param response
     * @return
     * @throws Exception
     */
    protected Category getCategoryFromJson(ResponseEntity<String> response)throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody());
        return this.getCategoryFromNode(rootNode);
    }

    /**
     * From a list of Categories Gets the them as Java objects.
     * @param categoryResponse The Response, checks for a OK status.
     * @return List of matching Category objects.
     * @throws Exception if an error occurs.
     */
    protected List<Category> getCategoriesFromResponse(ResponseEntity<String> categoryResponse) throws Exception{
        assertThat(categoryResponse.getStatusCode(), Matchers.is(OK));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(categoryResponse.getBody());
        JsonNode dataNode = rootNode.findPath("_embedded").get("categories");
        List<Category> categories = mapper.readValue(dataNode.toString(), new TypeReference<List<Category>>() {});
        return categories;
    }


    protected Category getCategoryFromNode(JsonNode rootNode) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.readValue(rootNode.toString(), Category.class);

        JsonNode dataNode = rootNode.findPath("_embedded").findPath("types");
        if (!dataNode.isMissingNode()) {
            Set<DataType> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<DataType>>() {});
            category.setTypes(data);
        }
        dataNode = rootNode.findPath("_embedded").findPath("resources");
        if (!dataNode.isMissingNode()) {
            Set<AdditionalResource> data = mapper.readValue(dataNode.toString(), new TypeReference<Set<AdditionalResource>>() {});
            category.setResources(data);
        }
        dataNode = rootNode.findPath("_embedded").findPath("training");
        if (!dataNode.isMissingNode()){
            Set<Training> data= mapper.readValue(dataNode.toString(), new TypeReference<Set<Training>>(){});
            category.setTraining(data);
        }
        dataNode = rootNode.findPath("_embedded").findPath("incidents");
        if (!dataNode.isMissingNode()){
            Set<Incident> data= mapper.readValue(dataNode.toString(), new TypeReference<Set<Incident>>(){});
            category.setIncidents(data);
        }
        dataNode = rootNode.findPath("_embedded").findPath("compliances");
        if (!dataNode.isMissingNode()){
            Set<Compliance> data= mapper.readValue(dataNode.toString(), new TypeReference<Set<Compliance>>(){});
            category.setCompliances(data);
        }
        dataNode = rootNode.findPath("_embedded").findPath("retentions");
        if (!dataNode.isMissingNode()){
            Set<Retention> data= mapper.readValue(dataNode.toString(), new TypeReference<Set<Retention>>(){});
            category.setRetentions(data);
        }

        // Category may have a Classification...
        dataNode = rootNode.findPath("_embedded").findPath("classification");
        if (!dataNode.isMissingNode()) {
            Classification data = mapper.readValue(dataNode.toString(), new TypeReference<Classification>() {});
            category.setClassification(data);
        }

        return category;
    }

    protected Classification getClassificationFromJson(JsonNode jsonNode)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonNode.toString(), Classification.class);
    }

    /**
     * Saves a Classification and returns the Id of it.
     * @param name The Name of the classification.
     * @return The Id of the newly saved classification
     */
    protected Long postClassification(String name){
        Classification classification = new Classification();
        classification.setName(name);
        classification.setDescription("the description");
        classification.setColor("red");
        classification.setPriority(1);

        ResponseEntity<Classification> classificationRepo = restTemplate.postForEntity (
                this.getLocalHostURL("/api/classification"), classification, Classification.class);

        assertThat(classificationRepo.getStatusCode(), is (HttpStatus.CREATED));
        return this.getIdFromHeaders(classificationRepo.getHeaders());
    }


    protected Long getIdFromHeaders(HttpHeaders headers){
        String uri = headers.getLocation().getPath();
        return this.getIdFromLocationURI(uri);
    }


    /**
     * Creates a Category and returns the Location Path URI.
     * @param categoryName The category name to use.
     * @return The Location header.
     */
    protected String postCategory(String categoryName){
        Category c = new Category();
        c.setName(categoryName);
        c.setDescription("desc");

        ResponseEntity<Category> response = restTemplate.postForEntity (
                this.getLocalHostURL("/api/category"), c, Category.class);

        assertThat("Should have headers from Category create", response.getHeaders(), Matchers.not(nullValue()));
        assertThat("Category create should respond with a CREATED",response.getStatusCode(), Matchers.is(HttpStatus.CREATED));
        assertThat("The create should have a body", response.getBody(), Matchers.not(nullValue()));
        return response.getHeaders().getLocation().getPath();
    }

    /**
     * Creates a New Tool and Returns the Location Path URI.
     * @param toolName The Tool Name.
     * @return The Location Header.
     */
    protected String postTool(String toolName) throws Exception {
        Tool t = new Tool();
        t.setName(toolName);
        t.setDescription("desc");
        t.setClassificationExceptions(true);

        ResponseEntity<String> response = restTemplate.postForEntity(
                this.getLocalHostURL("/api/tool"), t, String.class);

        Tool responseTool = this.getToolFromJson(response);

        assertThat("Should have headers from Tool create", response.getHeaders(), Matchers.not(nullValue()));
        assertThat("Tool create should respond with a CREATED",response.getStatusCode(), Matchers.is(HttpStatus.CREATED));
        assertThat("The create should have a body", response.getBody(), Matchers.not(nullValue()));

        assertThat("The Updator should be Bob", responseTool.getUpdater(), Matchers.is("bob"));
        assertThat("The lastModified should be set", responseTool.getUpdated(), notNullValue());

        return response.getHeaders().getLocation().getPath();
    }



    protected Long getIdFromLocationURI(String location){
        int startOfNumber=location.lastIndexOf("/");
        Long id =  Long.valueOf(location.substring(++startOfNumber));
        return id;
    }

    /**
     * Used to get the _Self URI link
     * @param response The body of a JSON response.
     * @return The self URI link String
     */
    protected String getSelfLink(ResponseEntity<String> response)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody());
        JsonNode selfNode = rootNode.findPath("_links").findPath("_self");
        return selfNode.asText();
    }

    /**
     * Gets the PersistedTool
     * @return Tool Object.
     * @throws Exception If a problem occurs.
     */
    protected Tool getPersistedTool(String toolURI)throws Exception{

        String url=this.getLocalHostURL(toolURI);
        ResponseEntity<String> response = restTemplate.getForEntity(
                url,
                String.class);
        assertThat(response.getStatusCode(), Matchers.is(OK));
        Tool tool =  this.getToolFromJson(response);
        tool.setId(this.getIdFromLocationURI(toolURI));
        return tool;
    }

    /**
     * Performs a put with the Tool Object.
     * @param tool The Tool.
     * @return ResponseEntity.
     * @throws Exception
     */
    protected ResponseEntity<String> putTool(Tool tool)throws Exception{

        String url = getLocalHostURL("/api/tool/update/"+tool.getId());
        ResponseEntity<String> response =  restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<Tool>(tool),
                String.class);
        return response;
    }

    protected ResponseEntity<String> deleteCategory(Long id){
        return restTemplate.exchange(this.getLocalHostURL(String.format("/api/category/%s", id)),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                String.class);
    }

    /**
     * From the response of a Search will extract the DataType result objects.
     * @param searchResponse The Search Response.
     * @return List of DataTypes.
     * @throws Exception If a Parse Exception occurs.
     */
    protected List<DataType> getDataTypesFromJsonSearchResponse(ResponseEntity<String> searchResponse) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(searchResponse.getBody());
        if (rootNode.get("_embedded")==null){
            return mapper.readValue(rootNode.toString(), new TypeReference<List<DataType>>() {});
        }
        JsonNode embedded = rootNode.get("_embedded");
        JsonNode types = embedded.get("types");
        return mapper.readValue(types.toString(), new TypeReference<List<DataType>>() {});
    }
}

