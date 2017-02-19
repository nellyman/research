package com.nbh.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.util.Scanner;

/**
 * From-
 * http://www.lucenetutorial.com/lucene-in-5-minutes.html
 * and
 * http://www.lucenetutorial.com/
 *
 * Created by nhardwic on 19/02/2017.
 */
public class SearchEngine {

    private StandardAnalyzer analyzer;
    private Directory index;
    private IndexWriter writer;
    private IndexWriterConfig config;

    public SearchEngine() throws Exception{
        analyzer=new StandardAnalyzer();
        index = new RAMDirectory();
        config = new IndexWriterConfig(analyzer);

        writer=new IndexWriter(index, config);
    }

    public void addDocument(String name, String description)throws Exception{
        Document doc = new Document();
        doc.add(new TextField("name", name, Field.Store.YES));
        doc.add(new TextField("description", description, Field.Store.YES));
        writer.addDocument(doc);
    }

    public void performSearch(String searchString)throws Exception{
        writer.close();
        String[] fields = {"name", "description"};
        Query query = new MultiFieldQueryParser(fields, analyzer).parse(searchString);

        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("name") + "\t" + d.get("description"));
        }
    }

    public static void main(String[] args)throws Exception{
        SearchEngine engine= new SearchEngine();
        // lets load the index
        System.out.println("Add entries to the index, press -- to stop adding.");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("\nname: ");
            String name = scanner.next();
            if ("--".equals(name)){
                break;
            }
            System.out.print("\ndescription: ");
            String description = scanner.next();
            engine.addDocument(name, description);
        }

        // lets do search...
        while(true) {
            System.out.print("Enter a search text (--) to end ");
            String searchText=scanner.next();
            if ("--".equals(searchText)){
                break;
            }
            engine.performSearch(searchText);
        }
    }
}
