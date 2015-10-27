package com.nbh.test;

import com.rbc.uk.common.util.StringHelper;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Apr 27, 2004
 * Time: 2:10:33 PM
 * To change this template use Options | File Templates.
 */
public class TruncateStringTest {

   //  private static  String txt=" aha only 12345678901234567890123456789012";

//     private static  String txt="1";

    private static  String txt="Napster, previously one of the best-known peer-to-peer sites, is the latest to join the rapidly-growing market for legally-downloadable music."+
"In the announcement on Thursday, Napster also said it was offering a free week-long trial of the full service."+
"In a deal with Dixons, Napster products, like branded blank CDs, CD wallets and CD labelling kits, as well as the service itself, will be promoted throughout its other stores, Curry's, PC World and The Link. 'P2P feel'"+
"Napster, now owned by US firm Roxio, has also struck a further deal with Samsung to offer a branded portable music player in the UK by the end of the year."+
"Mr Duea said Napster UK would provide a feel of the original file-sharing service, without breaching copyright."+
"The community elements feel like the old Napster, but it is all done securely and reliably."+
"He added that the service would be flexible, offering a choice of options."+
"We offer a subscription which we think is the ideal immersive music experience, but we are also offering a Napster store - Napster Light.";

    public void readFile(String filename){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String op = reader.readLine();
            System.out.println(op);

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }


    }


    public List getTruncatedText( String text, int paddedSize) throws Exception{
        List truncatedTexts = new ArrayList();
        if (text==null)
            return(truncatedTexts);

       // tokenize the string
       StringTokenizer st = new StringTokenizer(text);
       // pull back Strings upto limit size.
       String textElement="", token="";
         while(st.hasMoreTokens()){
             token = st.nextToken();
             // check to see if the token is bigger than the paddedSize...
             if (token.length()>paddedSize){
                 if (textElement.length()>0){
                    truncatedTexts.add(StringHelper.padLeftJustified(textElement, paddedSize, ' '));
                    textElement=new String("");
                 }
                 this.forceSplit(truncatedTexts, token, paddedSize);
                 token="";
             }

             if (textElement.length()+token.length()>=paddedSize){
                 textElement = StringHelper.padLeftJustified(textElement, paddedSize, ' ');
                 truncatedTexts.add(textElement);
                 textElement=new String("");
             }
             if (token.length()>0)
                textElement+=token+" ";
         }

        // finished looping round, the last token needs to be put onto the list
        if (textElement.length()>0){
            truncatedTexts.add(StringHelper.padLeftJustified(textElement, paddedSize, ' '));
        }
       return truncatedTexts;
    }
    private void forceSplit(List truncatedTexts, String token, int paddedSize){
        // token is large, so split at paddedSize...
        String txtSegment="";
        int pointer=0, strSize=token.length();
        while ((pointer+paddedSize)<strSize){
           txtSegment=token.substring(pointer, (pointer+paddedSize));
           truncatedTexts.add(StringHelper.padLeftJustified(txtSegment, paddedSize, ' '));
           pointer+=paddedSize;
        }
        // add remaing ...
        txtSegment=token.substring(pointer);
        truncatedTexts.add(StringHelper.padLeftJustified(txtSegment, paddedSize, ' '));
    }


    public String removeEOL(String text){
        String str="";
        BufferedReader reader = new BufferedReader(new StringReader(text));
        try {
            String tmp="";
            while (tmp!=null){
                tmp=reader.readLine();
                if (tmp!=null)
                    str+=(tmp+" ");
            }
        } catch (IOException e) {
            // failed to remove newlines, just return the text unchanged.
            str=text;
        }
        return str;

    }

    public static void main(String[] args) throws Exception{


        TruncateStringTest test= new TruncateStringTest();

       // test.readFile("f:\\text.txt");

        //test.begin();
        //test.removeEOL();
        List texts = test.getTruncatedText(txt, 30);
        Iterator it = texts.iterator();
        while (it.hasNext()){
            String txt =(String)it.next();
            System.out.println(txt+"* "+txt.length()+"\n");
        }
    }

}
