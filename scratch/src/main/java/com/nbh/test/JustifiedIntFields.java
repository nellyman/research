package com.nbh.test;

import com.rbc.uk.common.util.StringHelper;
import com.nbh.helper.LineReader;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: May 10, 2004
 * Time: 11:46:08 AM
 * To change this template use Options | File Templates.
 */
public class JustifiedIntFields {

private static int DECIMALPLACES=6;

    public void go() throws IOException{


        String field1=".0023";
        String field2="123456789123.987654321";
        String field3=".1035";
        String inputVal="";

        String field1Mod = getNumericWithDecimalPlaces(field1, 17);
        String field2Mod = getNumericWithDecimalPlaces(field2, 17);
        String field3Mod = getNumericWithDecimalPlaces(field3, 17);

        System.out.println("field1mod : "+field1Mod+" \t field1 : \t "+field1);
        System.out.println("field2mod : "+field2Mod+" \t field2 : \t "+field2);
        System.out.println("field3mod : "+field3Mod+" \t field3 : \t "+field3);

        while("-1".equals(inputVal)){
               inputVal = LineReader.getLineFromSystemIn();
               String results = getNumericWithDecimalPlaces(inputVal, 17);
                System.out.println(inputVal +" : to : "+results);
        }


    }

      public String getNumericWithDecimalPlaces(String field, int totalFieldLength){

        int toDot=field.indexOf('.');
        String decimals="", digits=field;
        if (toDot!=-1){
            decimals=field.substring((toDot+1), field.length());
            digits = field.substring(0,toDot);
        }
        System.out.println("( digits : "+digits+" ) ( decimals : "+decimals+" )");

        if (decimals.length()>DECIMALPLACES)
            decimals = decimals.substring(0,DECIMALPLACES);

        int digitLength =totalFieldLength-DECIMALPLACES;
        if (digits.length()>digitLength)
            digits = digits.substring(0,digitLength);

        decimals = StringHelper.padLeftJustified(decimals,DECIMALPLACES,'0');
        digits = StringHelper.padRightJustified(digits,digitLength,'0');

        return digits+decimals;
    }

    public String createNumericWithDecimalPlacesOld1(String field, int totalFieldLength, int decimalPlaces){
        int numberOfZerosToInsert=totalFieldLength-field.length();

        int toDot = field.indexOf('.');
        int numberOfDecimalsNeeded=(toDot==-1? decimalPlaces : decimalPlaces - ((field.length()-1)-toDot));
        System.out.println(" number of Zeros to insert : "+numberOfZerosToInsert+" number of dec needed "+numberOfDecimalsNeeded+" todot : "+toDot);
        if (toDot>totalFieldLength-decimalPlaces)
            System.out.println(" aha !! need to truncate the digits !!");
        // replace the .
        field = field.replaceAll("[.]","");
        if (numberOfDecimalsNeeded>0)
            field = StringHelper.padLeftJustified(field,field.length()+numberOfDecimalsNeeded,'0');
        // truncate the numbers at 6 dec places...
        else{
            field = field.substring(0, (toDot+decimalPlaces));
        }

        field = StringHelper.padRightJustified(field, totalFieldLength,'0');

        return field;
    }



    public String createNumericWithDecimalPlacesOld(String field, int totalFieldLength, int decimalPlaces){
       // ++decimalPlaces;
        int lengthToDot = field.indexOf('.');
        // we have a '.'
        if (lengthToDot>=0){
            field = field.replaceAll("[.]","");
            System.out.println("1. lengthToDot : "+lengthToDot);
            int numberOfZerosRequired = (decimalPlaces-(field.length()-lengthToDot))+field.length();
            System.out.println("2. number of zeros : "+ numberOfZerosRequired);
            field = StringHelper.padLeftJustified(field, numberOfZerosRequired, '0');
        }
        // no '.' need to add on zeros...
        else{

            field = padCharsIfPoss(field, decimalPlaces, true, '0');
        }
        field = StringHelper.padRightJustified(field, totalFieldLength,'0');
        return field;
    }

    private String padCharsIfPoss(String field, int numberOfChars, boolean left, char replaceChar){

        if (field.length()<numberOfChars){
            if (left)
                 field = StringHelper.padLeftJustified(field, numberOfChars, replaceChar);
            else
                 field = StringHelper.padRightJustified(field, numberOfChars, replaceChar);
        }
        return field;
    }


    public static void main(String[] args) throws Exception{
        JustifiedIntFields test = new JustifiedIntFields();
        test.go();
    }
}
