/*
 * ProductDAO.java
 *
 * Created on 10 June 2003, 11:43
 */

package com.nbh.research.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public ArrayList getProductsVO(){

        final ArrayList results = new ArrayList();
        final java.sql.Connection connection = SQLAccess.openConnection();
        try{
            final PreparedStatement ps = connection.prepareStatement("SELECT * from products");
            ps.executeQuery();
            final ResultSet rs = ps.getResultSet();
            while (rs.next()){
                final ProductVO product=new ProductVO(rs.getString("id"), rs.getString("name"), rs.getString("price"));
                results.add(product);
            }
        }
        catch(final SQLException sqle){

            System.err.println("Exception generated getting products");
        }
        finally{
            SQLAccess.returnConnection(connection);
        }
        return results;
    }

    @Override
    public boolean putProduct(final String name, final String price){

        boolean result=true;
        final Connection connection = SQLAccess.openConnection();
        try{
            final PreparedStatement ps = connection.prepareStatement("insert into products (name,price) values('?',?)");
            ps.setString(1, name);
            ps.setString(2, price);
            ps.executeUpdate();
        }
        catch(final SQLException sqle){
            System.err.println("Error generated updating database");
            result=false;
        }
        finally{
            SQLAccess.returnConnection(connection);
        }
        return result;
    }

    @Override
    public ProductVO getProduct(final int ID) {
        final Connection connection=SQLAccess.openConnection();
        ProductVO data=null;
        try{
            final PreparedStatement ps = connection.prepareStatement("select * from products where ID=?");
            ps.setString(1, String.valueOf(ID));
            final ResultSet rs = ps.executeQuery();
            rs.next();
            data=new ProductVO(rs.getString("id"), rs.getString("name"), rs.getString("price"));
        }
        catch(final SQLException sqle){
            System.err.println("Error getting information on product ID "+ID+" "+sqle.getMessage());
        }
        finally{
            SQLAccess.returnConnection(connection);
        }
        return data;
    }

}
