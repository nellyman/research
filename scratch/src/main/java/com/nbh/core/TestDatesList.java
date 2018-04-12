package com.nbh.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: May 20, 2004
 * Time: 3:48:46 PM
 * To change this template use Options | File Templates.
 */
public class TestDatesList {

    private List dateRecords = new ArrayList();
    private List dates = new ArrayList();

    public void go(){
        for (int i=0;i<54;i++){
            this.addEventDateInformation("records number "+i, "the date");
        }
        this.printRecords();
        System.out.println(" number of dateRecords "+dateRecords.size());
        System.out.println(" number of dates "+dates.size());
    }

    public void addEventDateInformation(String qualifier, String date){

        if (dateRecords.size()==0){
            dateRecords.add(dates);
        }
        String dateInfo = new String(qualifier+date);
        if (dates.size()>50){
            if (dateRecords.size()==1){
                dateRecords.add(dates);
            }
            dates= new ArrayList();
        }
        dates.add(dateInfo);
    }

    public void printRecords(){
        Iterator records = dateRecords.iterator();
        int rds=1, index=1;
        while (records.hasNext()){
            List dates = (List)records.next();
            Iterator dateIt = dates.iterator();
            System.out.println("left over : "+this.getSizeLeft(dates));
            while (dateIt.hasNext()){
                String data = (String)dateIt.next();
                System.out.println(" record : "+rds+" index : "+index+"  "+data);
                index++;
            }
            rds++;
            index=1;
        }
    }

    public int getSizeLeft(List dates){
        int size = dates.size();
        int totalSize = size*12;
        int leftOver = 612-totalSize;
        return leftOver;
    }

    public static void main(String[] args){

        TestDatesList test = new TestDatesList();
        test.go();
    }
}
