package com.nbh.test;

import com.nbh.data.QualifiedDataBean;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Apr 29, 2004
 * Time: 9:09:00 AM
 * To change this template use Options | File Templates.
 */
public class TestHashGeneration {

    private static int amount=100000;

    public static void main(String[] args){



        long start = System.currentTimeMillis();
        createObjects( true);
        long duration = System.currentTimeMillis()-start;
        System.out.println(" total time taken for instant hash generation: "+duration);

        start = System.currentTimeMillis();
        createObjects( false);
        duration = System.currentTimeMillis()-start;
        System.out.println(" total time taken for lazy hash generation: "+duration);

        start = System.currentTimeMillis();
        addObjectsToSet(true);
        duration = System.currentTimeMillis()-start;
        System.out.println(" total time taken for instant hash generation into a set: "+duration);

        start = System.currentTimeMillis();
        addObjectsToSet(true);
        duration = System.currentTimeMillis()-start;
        System.out.println(" total time taken for lazy hash generation into a set: "+duration);

        start = System.currentTimeMillis();
        addObjectsToList(true);
        duration = System.currentTimeMillis()-start;
        System.out.println(" total time taken for instant hash generation into a list: "+duration);

        start = System.currentTimeMillis();
        addObjectsToList(true);
        duration = System.currentTimeMillis()-start;
        System.out.println(" total time taken for lazy hash generation into a list: "+duration);

    }


    public static void createObjects(boolean imediateInstantiation){

        QualifiedDataBean bean = null;
        for (int loop=0;loop<amount;loop++){
            bean = new QualifiedDataBean("hello world "+loop, "123"+loop, imediateInstantiation);
        }

    }

    public static void addObjectsToSet(boolean imediateInstantiation){

        Set set = new HashSet();
        QualifiedDataBean bean = null;
        for (int loop=0;loop<amount;loop++){
            bean = new QualifiedDataBean("hello world "+loop, "123"+loop, imediateInstantiation);
            set.add(bean);
        }
    }

    public static void addObjectsToList(boolean imediateInstantiation){

        List list = new ArrayList();
        QualifiedDataBean bean = null;
        for (int loop=0;loop<amount;loop++){
            bean = new QualifiedDataBean("hello world "+loop, "123"+loop, imediateInstantiation);
            list.add(bean);
        }
    }

}
