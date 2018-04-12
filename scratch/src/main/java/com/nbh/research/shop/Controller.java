/*
 * Controller.java
 *
 * Created on 10 June 2003, 11:05
 */

package com.nbh.research.shop;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author  neal and rachel
 * @version
 */
public class Controller extends HttpServlet {

    private ProductsDelegate delegate;

    /** Initializes the servlet.
     */
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);

        //delegate = new ProductsDelegate(config.getServletContext());
        this.delegate = new ProductsDelegate();
        config.getServletContext().setAttribute("productDelegate", this.delegate);
    }

    /** Destroys the servlet.
     */
    @Override
    public void destroy() {

    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final String action =request.getParameter("action");
        if ("add".equals(action) || "remove".equals(action)){
            final String id = request.getParameter("id");
            Cart cart = (Cart)request.getSession().getAttribute("cart");
            if (cart==null) {
                cart=new Cart(this.getServletContext());
            }
            if ("add".equals(action)) {
                cart.addToCart(Integer.parseInt(id));
            } else {
                cart.releaseFromCart(Integer.parseInt(id));
            }
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("/cart.jsp").forward(request,response);
        }
        else{   // default, just list products
            final Collection products = this.delegate.getProducts();
            request.getSession().setAttribute("products",products);

            request.getRequestDispatcher("/products.jsp").forward(request,response);
        }
    }

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
