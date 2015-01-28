/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freeart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nathalie
 */
@WebServlet(name = "PanierServlet", urlPatterns = {"/PanierServlet"})
public class PanierServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        out.println("Je suis dans le DOGET de PanierServlet !<br/>");
        
        Cookie[] cookies = request.getCookies();
        boolean cookieTrouve = false;
        String ancienneValeur;
        
        if(cookies != null)
        {
            for(Cookie tousLesCookies : cookies)
            {
                if(tousLesCookies.getName().equals("listeImg"))
                {
                    cookieTrouve = true;
                    ancienneValeur = tousLesCookies.getValue();
                    tousLesCookies.setValue(ancienneValeur + ":" + request.getParameter("id"));
                    response.addCookie(tousLesCookies);
                }
            }
        }
        
        if(cookieTrouve == false)
        {
            Cookie cookie = new Cookie("listeImg", request.getParameter("id"));
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        } 
        
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
