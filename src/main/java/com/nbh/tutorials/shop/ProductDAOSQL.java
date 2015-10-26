/*
 * ProductDAO.java
 *
 * Created on 10 June 2003, 11:43
 */

package com.nbh.tutorials.shop;

import javax.servlet.*;
import java.util.ArrayList;
import java.sql.*;
import com.satsumas.sql.SQLAccess;

/**
 *
 * @author  neal and rachel
 */
public class ProductDAOSQL implements ProductDAO{
    
    /*private SQLAccess DAO;
     
    public ProductDAOSQL(ServletContext config) {
     
        DAO = (SQLAccess)config.getAttribute("sqlConnection");
        if (DAO==null)
            throw new RuntimeException("No connection to database exists in the servletcontext");
    }
     */
    
    public ArrayList getProductsVO(){
        
        ArrayList results = new ArrayList();
        java.sql.Connection connection = SQLAccess.openConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * from products");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                ProductVO product=new ProductVO(rs.getString("id"), rs.getString("name"), rs.getString("price"));
                results.add(product);
            }
        }
        catch(SQLException sqle){
            
            System.err.println("Exception generated getting products");
        }
        finally{
            SQLAccess.returnConnection(connection);
        }
        return results;
    }
    
    public boolean putProduct(String name, String price){
        
        boolean result=true;
        Connection connection = SQLAccess.openConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("insert into products (name,price) values('?',?)");
            ps.setString(1, name);
            ps.setString(2, price);
            ps.executeUpdate();
        }
        catch(SQLException sqle){
            System.err.println("Error generated updating database");
            result=false;
        }
        finally{
            SQLAccess.returnConnection(connection);
        }
        return result;
    }
    
    public ProductVO getProduct(int ID) {
        Connection connection=SQLAccess.openConnection();
        ProductVO data=null;
        try{
            PreparedStatement ps = connection.prepareStatement("select * from products where ID=?");
            ps.setString(1, String.valueOf(ID));
            ResultSet rs = ps.executeQuery();
            rs.next();
            data=new ProductVO(rs.getString("id"), rs.getString("name"), rs.getString("price"));
        }
        catch(SQLException sqle){
            System.err.println("Error getting information on product ID "+ID+" "+sqle.getMessage());
        }
        finally{
            SQLAccess.returnConnection(connection);
        }
        return data;
    }
    
}
