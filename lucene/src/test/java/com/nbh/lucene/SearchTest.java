package com.nbh.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;


/**
 * Created by nhardwic on 20/05/2016.
 */
public class SearchTest {

    private Logger logger= LoggerFactory.getLogger(Search.class);

    private static final String FIELDNAME="Content";
    private static final int hitsPerPage=10;

    private IndexSearcher indexSearcher=null;
    private Directory directory;

    @Before
    public void setup() throws Exception{
        directory = new RAMDirectory();
    }


    @Test
    public void shouldSearchWithStopWords() throws Exception{
        Analyzer analyzer = new WhitespaceAnalyzer();

        this.addDefaultDocumentsToIndex(analyzer);
        TopDocs docs =this.performSearch("This", FIELDNAME, analyzer);
        this.analyseOutput(2, docs);
    }

    @Test
    public void shouldNotMatchAsAnalyserIsCaseSensitive() throws Exception{
        Analyzer analyzer = new WhitespaceAnalyzer();

        this.addDefaultDocumentsToIndex(analyzer);
        TopDocs docs =this.performSearch("this", FIELDNAME, analyzer);
        this.analyseOutput(0, docs);
    }

    @Test
    public void shouldNotMatchAsAnalyserFiltersStopWords() throws Exception{
        Analyzer analyzer = new StandardAnalyzer();

        this.addDefaultDocumentsToIndex(analyzer);
        TopDocs docs =this.performSearch("This", FIELDNAME, analyzer);
        this.analyseOutput(0, docs);
    }

    @Test
    public void shouldMatchAsNotStopWordAndCaseInsensitiveAnalyser() throws Exception{
        Analyzer analyzer = new StandardAnalyzer();

        this.addDefaultDocumentsToIndex(analyzer);
        TopDocs docs =this.performSearch("lucene", FIELDNAME, analyzer);
        this.analyseOutput(2, docs);
    }

    @Test
    @Ignore("Unable to get Stemming to work")
    public void shouldMatchAsUsingStemmingAnalyser() throws Exception{
        Analyzer analyzer = new EnglishAnalyzer();

        this.addDefaultDocumentsToIndex(analyzer);
        TopDocs docs =this.performSearch("write", FIELDNAME, analyzer);
        this.analyseOutput(1, docs);
    }

    /**
     * Shows How an analyser works, tokenizing a String.
     * removes stop words and lowercases the words.
     */
    @Test
    public void shouldTokenizeStringWithStandardAnalyser(){
        Analyzer analyzer = new StandardAnalyzer();
        List<String> result = this.tokenizeString(analyzer);
        logger.info(result.toString());
    }

    /**
     * Only tokenizers on space
     */
    @Test
    public void shouldTokenizeStringWithWhitespaceAnalyser(){
        Analyzer analyzer = new WhitespaceAnalyzer();
        List<String> result = this.tokenizeString(analyzer);
        logger.info(result.toString());
    }

    /**
     * Stop makes lower case and removes stop words
     */
    @Test
    public void shouldTokenizeStringWithStopAnalyser(){
        Analyzer analyzer = new StopAnalyzer();
        List<String> result = this.tokenizeString(analyzer);
        logger.info(result.toString());
    }

    private List<String> tokenizeString(Analyzer analyzer){

        String string="The cat sat on the mat. Why did the cat sat on the mat?";

        List<String> result = new ArrayList<String>();
        try {
            TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
        } catch (IOException e) {
            // not thrown b/c we're using a string reader...
            throw new RuntimeException(e);
        }
        return result;
    }


    private void analyseOutput(int expectedResultNumber, TopDocs docs)throws Exception{
        ScoreDoc[] hits = docs.scoreDocs;
        int end = Math.min(docs.totalHits, hitsPerPage);
        logger.info("Total Hits: " + docs.totalHits);

        assertThat(docs.totalHits, is(expectedResultNumber));

        logger.info(" Results: ");
        IndexSearcher searcher  = this.getSearcher();
        for (int i = 0; i < end; i++) {
            Document d = searcher.doc(hits[i].doc);
            logger.info("Content: " + d.get("Content"));
        }
    }


    private IndexSearcher getSearcher() throws Exception{
        if (indexSearcher==null){
            IndexReader indexReader = DirectoryReader.open(directory);
            indexSearcher =new IndexSearcher(indexReader);
        }
        return indexSearcher;
    }



    private TopDocs performSearch(String queryString, String fieldName, Analyzer analyzer)throws Exception{

        IndexSearcher indexSearcher = this.getSearcher();

        QueryParser parser = new QueryParser(FIELDNAME, analyzer);
        Query query = parser.parse(queryString);

        int hitsPerPage = 10;
        return indexSearcher.search(query, hitsPerPage);
    }


    private void addDefaultDocumentsToIndex(Analyzer analyzer)throws Exception{

        PrintStream out =new PrintStream(System.out);
        IndexWriterConfig config= new IndexWriterConfig(analyzer).setInfoStream(out);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        Document doc = new Document();
        String text = "Lucene is an Information Retrieval library written in Java.";
        doc.add(new TextField(FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc);

        Document doc1 = new Document();
        text = "This has nothing to do with Lucene";
        doc1.add(new TextField(FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc1);

        Document doc2 = new Document();
        text = "This has nothing to do with Search";
        doc2.add(new TextField(FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc2);

        indexWriter.close();
    }

}
