package com.nbh.springutil;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import java.util.Map;
import java.util.HashMap;

public class BeanFactorySupplier{

	// we can refer to more than one factory here.
	private static Map factories=null;
	private static Map clients=null;

	/**
	* Provides a BeanFactory as specified in the spring xml descriptor
	* refered to in the argument. If the factory has already been identified
	* then the reference to the previously started factory is returned.
	* @param xmlFileNameOnClassPath String
	* @returns org.springframework.beans.factory.BeanFactory instance
	*/
	public static BeanFactory getFactory(String xmlFileNameOnClassPath){
		if (factories==null){
			factories=new HashMap();
			clients=new HashMap();
		}
		if (factories.containsKey(xmlFileNameOnClassPath)){
			addOneToClientList(xmlFileNameOnClassPath);
			return ((BeanFactory)factories.get(xmlFileNameOnClassPath));
		}else{
			//create a new instance of the bean factory...
			ClassPathResource resource = new ClassPathResource(xmlFileNameOnClassPath);
			BeanFactory factory = new XmlBeanFactory(resource);
			if (factory!=null){
				factories.put(xmlFileNameOnClassPath, factory);
				addOneToClientList(xmlFileNameOnClassPath);
				return factory;
			}
			throw new RuntimeException("Cannot find spring config "+xmlFileNameOnClassPath);
		}
	}

	private static void addOneToClientList(String key){
		int value=0;
		if (clients.containsKey(key)){
			value = ((Integer)clients.get(key)).intValue();
		}
		clients.put(key, new Integer(++value));
	}

	//TODO: when a factory gets returned the count needs reducing,
	// if zero then the factory should be removed.
	// need to think about testing this incl synchronisation.

	public static void returnFactory(BeanFactory factory){

	}

}