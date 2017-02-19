package com.nbh.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhardwic on 23/05/2016.
 */
public class TokenizerTest {

    private Logger logger= LoggerFactory.getLogger(TokenizerTest.class);

    private static final String TEST_STRING="The cat sat on the mat. Why did the cat sat on the mat?";

    /**
     * Shows How an analyser works, tokenizing a String.
     * removes stop words and lowercases the words.
     */
    @Test
    public void shouldTokenizeStringWithStandardAnalyser(){
        Analyzer analyzer = new StandardAnalyzer();
        List<String> result = SearchUtils.tokenizeString(analyzer, TEST_STRING);
        logger.info("standard analyser");
        logger.info(result.toString());
    }

    /**
     * Only tokenizers on space
     */
    @Test
    public void shouldTokenizeStringWithWhitespaceAnalyser(){
        Analyzer analyzer = new WhitespaceAnalyzer();
        List<String> result = SearchUtils.tokenizeString(analyzer, TEST_STRING);
        logger.info("whitespace analyser");
        logger.info(result.toString());
    }

    /**
     * Stop makes lower case and removes stop words
     */
    @Test
    public void shouldTokenizeStringWithStopAnalyser(){
        Analyzer analyzer = new StopAnalyzer();
        List<String> result = SearchUtils.tokenizeString(analyzer, TEST_STRING);
        logger.info("stop analyser");
        logger.info(result.toString());
    }



}
