/*
 * ListProductsTag.java
 *
 * Created on 10 June 2003, 20:14
 */

package com.nbh.research.shop;

import java.util.*;
import java.io.IOException;
/**
 *
 * @author  neal and rachel
 */
public class ListProductsTag extends javax.servlet.jsp.tagext.TagSupport {
    
    Iterator  products=null;
    int count;
    /** Creates a new instance of ListProductsTag */
    
    
    public int doStartTag(){
        
        int returnType=EVAL_BODY_INCLUDE;
        products=((Collection)pageContext.getSession().getAttribute("products")).iterator();
        if (products==null)            
            returnType = SKIP_BODY;
        return returnType;
    }
    
    public int doAfterBody(){
        
        int returnType = SKIP_BODY;
        
        while(products.hasNext()){
            java.io.Writer writer=pageContext.getOut();
            ProductVO product = (ProductVO)products.next();
            try{
                writer.write(product.getID()+">"+product.getName()+"</a>"+product.getPrice());
                returnType=EVAL_BODY_AGAIN;                
            }catch(IOException ioe){                
                System.err.println("error displaying products");
            }
        }
        return returnType;
    }
}
