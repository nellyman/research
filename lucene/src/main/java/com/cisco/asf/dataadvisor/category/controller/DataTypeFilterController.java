package com.cisco.asf.dataadvisor.category.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataTypeFilterController {

    private static final String ESCAPED_QUERY_CHARS="[%\"<>|]";

    @Autowired
    private DataTypeRepository dataTypeRepository;

    @Autowired
    private SimpleSearchService<DataType> simpleSearchService;

    @Autowired
    private SearchIndexService<DataType> searchIndexService;


    @RequestMapping(path = "/filter")
    @ResponseBody
    public List<DataType> search(@RequestParam("name")String name)throws IOException {
        // clean the name...
        if (name==null || "".equals(name)){
            return new ArrayList<DataType>(0);
        }
        return simpleSearchService.search(name);
    }

    /**
     * Resets the Search. Note that this is node specific.
     * Also user is required to be admin.
     * @return OK if reset is successful.
     * @throws Exception if a problem occurs.
     */
    @RequestMapping(path = "/admin/search/reset")
    @PreAuthorize("hasAnyRole('powerAdmin', 'categoryAdmin') ")
    public ResponseEntity<String> reset()throws IOException{
        searchIndexService.createIndex();
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}

