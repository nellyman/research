/*
 * ProductsDelegate.java
 *
 * Created on 10 June 2003, 13:52
 *
 *  An example of the business deletage pattern. Requests from the controller
 * servlet hit this object to get the products list. The underlying connection to the
 * database is therefore encapsulated away.
 * 
 **/ 

package com.nbh.tutorials.shop;

import java.util.*;
import javax.servlet.*;
/**
 *
 * @author  neal and rachel
 */
public class ProductsDelegate {
    
    private ProductDAO dao;
    
    /** Creates a new instance of ProductsDelegate */
 /*   public ProductsDelegate(ServletContext config) {
        dao = new ProductDAOSQL(config);        
    }
*/
    public ProductsDelegate() {
        dao = new ProductDAOSQL();        
    }
  
    public Collection getProducts(){
        return dao.getProductsVO();        
    }
    
    public boolean putProduct(String name, String price){
     
        return dao.putProduct(name, price);
    }
    public ProductVO getProduct(int ID){
     
        return dao.getProduct(ID);
    }
}
