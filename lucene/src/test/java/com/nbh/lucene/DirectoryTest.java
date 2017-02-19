package com.nbh.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by nhardwic on 23/05/2016.
 */
public class DirectoryTest {

    private static Logger logger= LoggerFactory.getLogger(DirectoryTest.class);

    private static final String TEST_STRING="The cat sat on the mat. Why did the cat sat on the mat?";
    public static final String CONTENT_FIELDNAME ="Content";

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
    public void shouldExamineIndex() throws Exception{
        Analyzer analyzer = new WhitespaceAnalyzer();

        IndexWriter indexWriter = SearchUtils.getIndexWriter(analyzer, directory);

        Document doc = new Document();
        String text = "Lucene is an Information Retrieval library written in Java.";
        doc.add(new TextField(CONTENT_FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc);

        doc = new Document();
        text = "This has nothing to do with Lucene.";
        doc.add(new TextField(CONTENT_FIELDNAME, text, Field.Store.YES));
        indexWriter.addDocument(doc);

        indexWriter.close();

        IndexReader reader = SearchUtils.getIndexReader(directory);

        logger.info(reader.toString());

        // wished to inspect the reader...
    }

}
