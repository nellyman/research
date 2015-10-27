/*
 * ProductDAO.java
 *
 * Created on 10 June 2003, 12:04
 */

package com.nbh.tutorials.shop;

import java.util.ArrayList;
/**
 *
 * @author  neal and rachel
 */
public interface ProductDAO {
    
    public ArrayList getProductsVO();
    
    public boolean putProduct(String name, String price);
    
    public ProductVO getProduct(int ID);
}
