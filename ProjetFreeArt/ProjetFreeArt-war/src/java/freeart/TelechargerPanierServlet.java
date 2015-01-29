/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freeart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
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
@WebServlet(name = "TelechargerPanierServlet", urlPatterns = {"/TelechargerPanierServlet"})
public class TelechargerPanierServlet extends HttpServlet {


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
        
        String UPLOAD_DIRECTORY = this.getClass().getResource('/' + this.getClass().getName().replace('.', '/') + ".class").toString().substring(6,97) + "web/";

        
        PrintWriter out = response.getWriter();
        
        out.println("Kikoo panier");
        
        String listePanier = "";
        String[] imgDansPanier;
        List<Creation> maListeImage = new ArrayList<Creation>();
        List<Creation> resCrea = freeart.EJBCreation.getCreations();
        
        out.println("Je suis dans afficherPanier");        
        Cookie[] cookies = request.getCookies();
                
        if(cookies != null)
        {
            for(Cookie tousLesCookies : cookies)
            {
                if(tousLesCookies.getName().equals("listeImg"))
                {
                    listePanier = tousLesCookies.getValue();
                }
            }
        }
        
        imgDansPanier = listePanier.split(":");
       
        for(Creation toutesLesCreations : resCrea)
        {
            for(String lienImg : imgDansPanier)
            {
                if(toutesLesCreations.getFile().equals(lienImg))
                {
                    maListeImage.add(toutesLesCreations);
                }
            }
        }
        
               
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
