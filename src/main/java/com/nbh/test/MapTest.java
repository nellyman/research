package com.nbh.test;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


/**
* class to test the removal of a elements in a Map.
* Will remove elements from the Map by using the iterator.remove()
* method. This method will remove the last element returned by the
* iterator. It gets round the multiple access exception.
*
*
*
**/
public class MapTest{

	private Map map;


	public MapTest(){

		map =new HashMap();
	}

	public void loadRandomData(int maxValue){
		Integer val;
		for (int i=0;i<10;i++){
			val = 	new Integer((int)(Math.random()*maxValue));
			map.put(new Integer(i), val);
		}

	}

	public void deleteEntriesUnder(int threshold){
		Iterator it = map.keySet().iterator();
		Integer val, key;
		while (it.hasNext()){
			key = (Integer)it.next();
			val = (Integer)map.get(key);
			if (val.intValue()>threshold){
				it.remove();
			}
		}
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		Iterator it = map.keySet().iterator();
		Integer val, key;
		while (it.hasNext()){
			key = (Integer)it.next();
			val = (Integer)map.get(key);
			sb.append("key : "+key+"  "+val+"\r\n");
		}
		return sb.toString();
	}

	public static void main(String[] args){

		MapTest mapObj = new MapTest();
		mapObj.loadRandomData(100);
		System.out.println(mapObj.toString());
		mapObj.deleteEntriesUnder(50);
		System.out.println(mapObj.toString());
	}

}
