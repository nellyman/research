/*
 * Cart.java
 *
 * Created on 10 June 2003, 14:54
 */

package com.nbh.research.shop;

import java.util.*;
import javax.servlet.*;

/**
 *
 * @author  neal and rachel
 */
public class Cart {
    
    List items;
    ProductsDelegate delegate;
    
    /** Creates a new instance of Cart */
    public Cart(ServletContext context) {
        delegate = (ProductsDelegate)context.getAttribute("productDelegate");
        items=new ArrayList(3);
    }
    
    public void addToCart(int ID){
     
        // go to database and get the ProductVO matching this ID       
        ProductVO product = delegate.getProduct(ID);
        if (product!=null)
            items.add(product);
        else
            System.err.println("Product ID "+ID+" not found to add to cart");
    }
    
    public void releaseFromCart(int ID){
        Iterator it = items.iterator();
        found:
        while (it.hasNext()){
            ProductVO product=(ProductVO)it.next();
            if ((Integer.parseInt(product.getID()))==ID){
                it.remove();
                break found;
            }
        }
    }
    
    public Collection getItems(){
        return items;
    }
}
