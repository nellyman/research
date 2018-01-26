package com.cisco.asf.dataadvisor.category.controller;

import com.cisco.asf.dataadvisor.category.model.DataType;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DataTypeFilterControllerTest extends SpringTestContextRequired{

    @Autowired
    private DataTypeFilterController dataTypeFilterController;

    @Test
    public void shouldFilterDataAndGetCategoriesForUpperCaseEntry(){
        List<DataType> dataTypeList = dataTypeFilterController.filter("DATA");
        this.inspectResults(dataTypeList, 10);

        DataType dt = dataTypeList.get(0);
        assertThat("Expecting Announced Financial Data", dt.getName(), is("Announced Financial Data"));
    }

    @Test
    public void shouldFilterDataAndGetCategoriesForTwoWordInput(){
        List<DataType> dataTypeList = dataTypeFilterController.filter("customer NAME");
        this.inspectResults(dataTypeList, 1);

        DataType dt = dataTypeList.get(0);
        assertThat("Expecting Customer name", dt.getName(), is("Customer name"));
    }

    @Test
    public void shouldHandleNullInput(){
        List<DataType> dataTypeList = dataTypeFilterController.filter(null);
        this.inspectResults(dataTypeList, 0);
    }

    @Test
    public void shouldHandleInvalidInput(){
        List<DataType> dataTypeList = dataTypeFilterController.filter("%");
        this.inspectResults(dataTypeList, 0);
    }


    private void inspectResults(List<DataType> dataTypeList, int expectedSize ){
        assertNotNull("Data Type should not be null", dataTypeList);
        assertThat("expect "+expectedSize+" DataTypes returned.", dataTypeList.size(), is(expectedSize));
    }
}