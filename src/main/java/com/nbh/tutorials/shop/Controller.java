/*
 * Controller.java
 *
 * Created on 10 June 2003, 11:05
 */

package com.nbh.tutorials.shop;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.satsumas.sql.SQLAccess;
/**
 *
 * @author  neal and rachel
 * @version
 */
public class Controller extends HttpServlet {
    
    private ProductsDelegate delegate;
    
    /** Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        //delegate = new ProductsDelegate(config.getServletContext());
        delegate = new ProductsDelegate();
        config.getServletContext().setAttribute("productDelegate", delegate);
    }
    
    /** Destroys the servlet.
     */
    public void destroy() {
        
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String action =request.getParameter("action");
        if ("add".equals(action) || "remove".equals(action)){
            String id = request.getParameter("id");
            Cart cart = (Cart)request.getSession().getAttribute("cart");
            if (cart==null)
                cart=new Cart(this.getServletContext());
            if ("add".equals(action))
                cart.addToCart(Integer.parseInt(id));
            else
                cart.releaseFromCart(Integer.parseInt(id));
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("/cart.jsp").forward(request,response);
        }
        else{   // default, just list products
            Collection products = delegate.getProducts();
            request.getSession().setAttribute("products",products);
            
            request.getRequestDispatcher("/products.jsp").forward(request,response);
        }
    }
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    
}
