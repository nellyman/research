package com.nbh.lucene;

    import org.apache.lucene.analysis.Analyzer;
    import org.apache.lucene.analysis.TokenStream;
    import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
    import org.apache.lucene.index.DirectoryReader;
    import org.apache.lucene.index.IndexReader;
    import org.apache.lucene.index.IndexWriter;
    import org.apache.lucene.index.IndexWriterConfig;
    import org.apache.lucene.queryparser.classic.QueryParser;
    import org.apache.lucene.search.IndexSearcher;
    import org.apache.lucene.search.Query;
    import org.apache.lucene.search.TopDocs;
    import org.apache.lucene.store.Directory;
    import org.apache.lucene.store.RAMDirectory;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.io.IOException;
    import java.io.PrintStream;
    import java.io.StringReader;
    import java.util.ArrayList;
    import java.util.List;

    import static org.hamcrest.CoreMatchers.is;
    import static org.hamcrest.MatcherAssert.assertThat;

    /**
     * Created by nhardwic on 23/05/2016.
     */
    public class SearchUtils {

   private static Logger logger= LoggerFactory.getLogger(SearchUtils.class);

    public static Directory getDirectory(){
            return new RAMDirectory();
    }

    public static void clearDirectory(Directory directory) throws Exception{
        directory.close();
    }



    public static TopDocs performSearch(String queryString, String fieldName, Analyzer analyzer, Directory directory)throws Exception{

        IndexSearcher indexSearcher =new IndexSearcher(getIndexReader(directory));

        QueryParser parser = new QueryParser(fieldName, analyzer);
        Query query = parser.parse(queryString);

        int hitsPerPage = 10;
        return indexSearcher.search(query, hitsPerPage);
    }



    public static IndexReader getIndexReader(Directory directory) throws Exception{
            return DirectoryReader.open(directory);
    }

        /**
         * Gets an IndexWriter to write documents into the Directory and index them with the supplied analyzer
         * @param analyzer The Analyzer to index documents.
         * @param directory the Directory that the writer will write to.
         * @return IndexWriter.
         * @throws Exception
         */
    public static IndexWriter getIndexWriter(Analyzer analyzer, Directory directory) throws Exception{
        PrintStream out =new PrintStream(System.out);
        IndexWriterConfig config= new IndexWriterConfig(analyzer).setInfoStream(out);
        return new IndexWriter(directory, config);
    }




        /**
         * Used in Tokenizer tests..
         * @param analyzer
         * @param data
         * @return
         */
        public static List<String> tokenizeString(Analyzer analyzer, String data){

            List<String> result = new ArrayList<String>();
            try {
                TokenStream stream  = analyzer.tokenStream(null, new StringReader(data));
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
}
