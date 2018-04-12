/*
 * CollectionsComparer.java
 *
 * Created on 07 May 2003, 09:36
 */

package com.nbh.core;

import java.util.*;
/**
 *
 * @author  neal and rachel
 */
public class CollectionsComparer {
    
    public int elements = 10000;
    public int tests=500;
    List list;
    Map map;
    Set set1;
    Set set2;
    
    /** Creates a new instance of CollectionsComparer */
    public CollectionsComparer() {
        list = new ArrayList(10000);
        map = new HashMap(10000);
        set1 = new TreeSet(new Comparator(){         
            public int compare(Object one, Object two){
                Integer p = (Integer)one;
                Integer q = (Integer)two;
                return (p.compareTo(q));                
            }            
        });
        set2 = new HashSet(10000);
        
        
        list = (List)this.load(list,"ArrayList");
        set1 = (Set)this.load(set1,"TreeSet");        
        set2 = (Set)this.load(set2,"HashSet");
        // map = (Map)this.load(map,"map");     // note a HashMap isn't subclass of a collection
        testCollection(list,"ArrayList");
        testCollection(set1, "TreeSet");
        testCollection(set2,"HashSet");
    }
    
    public Collection load(Collection coll, String type){
        long start = Calendar.getInstance().getTimeInMillis();
        Random rand = new Random();
        int i=0;
        while (i++<elements){ 
            Integer num = new Integer( rand.nextInt());
            coll.add(num);
        }
        long listLoaded = Calendar.getInstance().getTimeInMillis();        
        System.out.println(elements+" element "+type+" loaded in "+(listLoaded-start)+" mS");        
        return coll;
    }
    
    public void testCollection(Collection coll,String type){
        long start = Calendar.getInstance().getTimeInMillis();
        Random rand = new Random();
        int i=0;
        while (i++<tests){
            Integer num = new Integer( rand.nextInt()); // will test the searching of the collection
            coll.remove(num);                
        }
        i=0;
        while (i++<tests){
            Integer num = new Integer( rand.nextInt()); // will test the additions of the collections
            coll.add(num);
        }
        long testFinished = Calendar.getInstance().getTimeInMillis();          
        System.out.println(type+" tests completed in "+(testFinished-start)+" mS");        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CollectionsComparer ct = new CollectionsComparer();
    }
    
}
