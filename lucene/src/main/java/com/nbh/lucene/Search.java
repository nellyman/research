package com.nbh.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import org.apache.lucene.util.Version;


/**
 * http://www.avajava.com/tutorials/lessons/how-do-i-use-lucene-to-index-and-search-text-files.html
 *
 * Created by nhardwic on 11/05/2016.
 */
public class Search {

    private static final String FIELDNAME="Content";

    public static void main(String[] args) throws Exception{

        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new RAMDirectory();
        IndexWriterConfig config= new IndexWriterConfig(analyzer);
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


        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser parser = new QueryParser(FIELDNAME, analyzer);
        Query query = parser.parse("Lucene");

        int hitsPerPage = 10;
        TopDocs docs = indexSearcher.search(query, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;
        int end = Math.min(docs.totalHits, hitsPerPage);
        System.out.println("Total Hits: " + docs.totalHits);
        System.out.println(" Results: ");
        for (int i = 0; i < end; i++) {
            Document d = indexSearcher.doc(hits[i].doc);
            System.out.println("Content: " + d.get("Content"));
        }

    }
}
