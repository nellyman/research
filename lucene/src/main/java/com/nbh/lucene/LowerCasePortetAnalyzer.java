package com.nbh.lucene;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;

import java.io.Reader;

/**
 * Based on the Standard Analyzer but with additional PorterStemFilter (!!)
 * Created by nhardwic on 19/02/2017.
 */
public class LowerCasePortetAnalyzer extends StopwordAnalyzerBase {

    private int maxTokenLength = StandardTokenizer.MAX_TOKEN_LENGTH_LIMIT;

    protected TokenStreamComponents createComponents(String fieldName) {
        final StandardTokenizer src = new StandardTokenizer();
        src.setMaxTokenLength(maxTokenLength);
        TokenStream tok = new StandardFilter(src);
        tok = new LowerCaseFilter(tok);
        tok = new StopFilter(tok, stopwords);
        //tok = new PorterStemFilter(tok);
        tok = new SnowballFilter(tok, "English");
        return new TokenStreamComponents(src, tok) {
            @Override
            protected void setReader(final Reader reader) {
                int m = LowerCasePortetAnalyzer.this.maxTokenLength;
                ((StandardTokenizer)src).setMaxTokenLength(m);
                super.setReader(reader);
            }
        };
    }
}
