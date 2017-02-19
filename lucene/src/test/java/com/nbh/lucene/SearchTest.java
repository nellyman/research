package com.nbh.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;


/**
 * Created by nhardwic on 20/05/2016.
 */
public class SearchTest {

    public static final int HITS_PER_PAGE =10;
    public static final String CONTENT_FIELDNAME ="Content";
    private Logger logger= LoggerFactory.getLogger(Search.class);

    private Directory directory;

    @Before
    public void setup(){
        directory =SearchUtils.getDirectory();
    }

    @After
    public void clearup()throws Exception{
        SearchUtils.clearDirectory(directory);
    }


    @Test
    public void shouldSearchWithStopWords() throws Exception{
        Analyzer analyzer = new WhitespaceAnalyzer();

        addDefaultDocumentsToIndex(directory, analyzer);
        TopDocs docs =SearchUtils.performSearch("This", CONTENT_FIELDNAME, analyzer, directory);
        this.analyseOutput(2, docs);
    }

    @Test
    public void shouldNotMatchAsAnalyserIsCaseSensitive() throws Exception{
        Analyzer analyzer = new WhitespaceAnalyzer();

        addDefaultDocumentsToIndex(directory, analyzer);
        TopDocs docs =SearchUtils.performSearch("this", CONTENT_FIELDNAME, analyzer, directory);
        this.analyseOutput(0, docs);
    }

    @Test
    public void shouldNotMatchAsAnalyserFiltersStopWords() throws Exception{
        Analyzer analyzer = new StandardAnalyzer();

        addDefaultDocumentsToIndex(directory, analyzer);
        TopDocs docs =SearchUtils.performSearch("This", CONTENT_FIELDNAME, analyzer, directory);
        this.analyseOutput(0, docs);
    }

    @Test
    public void shouldMatchAsNotStopWordAndCaseInsensitiveAnalyser() throws Exception{
        Analyzer analyzer = new StandardAnalyzer();

        addDefaultDocumentsToIndex(directory, analyzer);
        TopDocs docs =SearchUtils.performSearch("lucene", CONTENT_FIELDNAME, analyzer, directory);
        this.analyseOutput(2, docs);
    }

    @Test
    @Ignore("Unable to get Stemming to work")
    public void shouldMatchAsUsingStemmingAnalyser() throws Exception{
        Analyzer analyzer = new EnglishAnalyzer();

        addDefaultDocumentsToIndex(directory, analyzer);
        TopDocs docs =SearchUtils.performSearch("write", CONTENT_FIELDNAME, analyzer, directory);
        this.analyseOutput(1, docs);
    }

    public void analyseOutput(int expectedResultNumber, TopDocs docs)throws Exception{
        ScoreDoc[] hits = docs.scoreDocs;
        int end = Math.min(docs.totalHits, HITS_PER_PAGE);
        logger.info("Total Hits: " + docs.totalHits);

        assertThat(docs.totalHits, is(expectedResultNumber));

        logger.info(" Results: ");

        IndexSearcher searcher  = new IndexSearcher(SearchUtils.getIndexReader(directory));
        for (int i = 0; i < end; i++) {
            Document d = searcher.doc(hits[i].doc);
            logger.info("Content: " + d.get("Content"));
        }
    }


    /**
     * Writes Default documents into the Directory.
     * @param directory The directory to write into.
     * @param analyzer the analyzer to index the documents with.
     * @throws Exception
     */
    public static void addDefaultDocumentsToIndex(Directory directory, Analyzer analyzer)throws Exception{

        IndexWriter indexWriter = SearchUtils.getIndexWriter(analyzer, directory);

        Document doc = new Document();
        String text = "Lucene is an Information Retrieval library written in Java.";
        doc.add(new TextField(CONTENT_FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc);

        Document doc1 = new Document();
        text = "This has nothing to do with Lucene";
        doc1.add(new TextField(CONTENT_FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc1);

        Document doc2 = new Document();
        text = "This has nothing to do with Search";
        doc2.add(new TextField(CONTENT_FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc2);

        indexWriter.close();
    }


}
