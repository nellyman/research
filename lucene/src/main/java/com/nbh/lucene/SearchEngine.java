package com.nbh.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
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
 * https://lucene.apache.org/core/2_9_4/queryparsersyntax.html#Wildcard Searches
 *
 * The boy ran to the store.
 The boy is running to the store.
 The boy will run to the store.
 The boy runs to the store every day.
 The boy will grow up to be a runner,
 *
 * Created by nhardwic on 19/02/2017.
 */
public class SearchEngine {

    private Analyzer analyzer;
    private Directory index;
    private IndexWriter writer;
    private IndexWriterConfig config;

    /**
     * Initialises the core objects.
     * Expects the Analyzer to be already initialised.
     */
    private void initializeCore()throws Exception{
        index = new RAMDirectory();
        config = new IndexWriterConfig(analyzer);

        writer=new IndexWriter(index, config);
    }

    public SearchEngine() throws Exception{
        analyzer=new StandardAnalyzer();
        this.initializeCore();
    }

    public SearchEngine(Analyzer anal)throws Exception{
        this.analyzer = anal;
        this.initializeCore();
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
        // need to select the Analyser to be used...
        System.out.println("Select the Analyzer engine:");
        System.out.println("1: Standard");
        System.out.println("2: English");
        System.out.println("3: Customer PorterAnalyzer");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();

        SearchEngine engine;
        switch (input){
            case 2:{
                engine=new SearchEngine(new EnglishAnalyzer());
                break;
            }
            case 3:{
                engine = new SearchEngine(new LowerCasePortetAnalyzer());
                break;
            }
            default:{
                engine = new SearchEngine(new StandardAnalyzer());
            }
        }

        scanner.nextLine();
        // lets load the index
        engine.addDocument("run", "run is tiring");
        engine.addDocument("ran", "I ran quickly");
        engine.addDocument("runner", "The runner is quick");
        engine.addDocument("runs", "The boy runs");
        engine.addDocument("running", "I like running");

        engine.addDocument("decide", "I decide what to do");
        engine.addDocument("decided", "I had decided to do that");
        engine.addDocument("decides", "Who decides that?");
        engine.addDocument("deciding", "I am deciding now!");

        engine.addDocument("know", "I know that");
        engine.addDocument("knowledge", "I have a good knowledge");
        engine.addDocument("knowing", "I am knowing");
        engine.addDocument("knows", "He knows this");

        engine.addDocument("policy", "The Policy");
        engine.addDocument("policies", "The Policies");


       /* System.out.println("Add entries to the index, press 'q' to stop adding.");

        while(true) {
            System.out.print("\nname: ");
            String name = scanner.nextLine();
            if ("q".equals(name)){
                break;
            }
            System.out.print("\ndescription: ");
            String description = scanner.nextLine();
            engine.addDocument(name, description);
        }*/

        // lets do search...
        while(true) {
            System.out.print("Enter a search text ('q') to end ");
            String searchText=scanner.nextLine();
            System.out.println("Search on: "+searchText);
            if ("q".equals(searchText)){
                break;
            }
            engine.performSearch(searchText);
        }
    }

}
