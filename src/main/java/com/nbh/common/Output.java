package com.nbh.common;

import org.apache.log4j.Logger;
import java.util.List;

public class Output{

    private static Logger log = Logger.getLogger(Output.class);

    public static void printResults(List results){
		for (int i=0;i<results.size();i++){
			   log.info("****** RESULTS: "+i+" : "+results.get(i));
        }
	}
}