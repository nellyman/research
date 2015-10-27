package com.nbh.test;

import com.nbh.other.AccountInformation;
import com.nbh.other.OptionInformation;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Apr 13, 2004
 * Time: 1:38:51 PM
 * To change this template use Options | File Templates.
 */
public class HashTest {

    public static void main(String[] args){


        AccountInformation acct1 = new AccountInformation(1234567890,"6454","sav");
        AccountInformation acct2 = new AccountInformation(123,"60","cur");
        AccountInformation acct3 = new AccountInformation(123,"6045","cur");

        System.out.println("Account equalsTest : ");
        testEquality(acct1,acct2);

        OptionInformation opt1 = new OptionInformation("123","f","$","FFR","29.75");
        OptionInformation opt2 = new OptionInformation("123","e","+","GBP","29.50");

        System.out.println("Options equalsTest : ");
        testEquality(opt1, opt2);

        System.out.println("putting 2 acct's (matching account numbers) in a List, looking at the size -");
        List list = new ArrayList();
        list.add(acct1);
        list.add(acct2);
        System.out.println("we have : "+list.size()+ " elements ");

        System.out.println("putting 3 acct's (matching account numbers) in a Set, looking at the size -");
        Set s = new HashSet();
        s.add(acct1);
        s.add(acct2);
        s.add(acct3);
        System.out.println("we have : "+s.size()+ " elements ");
        // sets do not allow duplicate elements....

    }

    public static void testEquality(Object obj1, Object obj2){

        System.out.println("hashCode 1 : "+obj1.hashCode()+"  hashCode 2 : "+obj2.hashCode());
        if (obj1.equals(obj2)){
            System.out.println("equals 1 - 2 !");
        }
        if (obj2.equals(obj1)){
            System.out.println("equals 2 - 1 !");
        }
        System.out.println(" test against null ");
        if (obj1.equals(null)){
            System.out.println("equals : null !");
        }
        else
            System.out.println("not equal null ..");

        System.out.println("Completed tests for "+obj1.getClass().getName());
    }

}
