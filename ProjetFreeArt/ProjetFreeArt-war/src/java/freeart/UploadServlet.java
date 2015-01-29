/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freeart;

import static freeart.ConnexionServlet.ATT_SESSION_USER;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Nathalie
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {
    
    //private final String UPLOAD_DIRECTORY = "C:/temp";
    private final String UPLOAD_DIRECTORY = this.getClass().getResource('/' + this.getClass().getName().replace('.', '/') + ".class").toString().substring(6,97) + "web/img";

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
        out.println("Je suis dans le goget de servletupload");
        
        SessionFactory sessionFactory;

            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Categorie> lc = new ArrayList<>();
            List<Categorie> listeCat = new ArrayList<Categorie>();
            
            try
            {
                lc = session.createQuery( "from Categorie" ).list();

                for(Categorie cat : lc)
                {
                    listeCat.add(cat);//.getFile());
                }
            } 
            catch (NumberFormatException ex)
            {
                lc = null;
            }

                request.setAttribute("cat", listeCat);

                RequestDispatcher rd = request
                                .getRequestDispatcher("/upload.jsp");
                rd.forward(request, response);
        
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
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        String name = "";
        int poids = 0;
        int width;
        int height;
        String desc = "";
        String nomImg = "";
        int categorieId = -1;
        int w=0;
        int h=0;
        
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        //name = new File(item.getName()).getName();
                        
                        List<Creation> lc = new ArrayList<Creation>();
                        
                        SessionFactory sessionFactory;

                    // A SessionFactory is set up once for an application
                    sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                    // create a couple of events...
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();
                    
                    lc = session.createQuery( "select max(id) from Creation").list();

                    
                    session.getTransaction().commit();
                    session.close();

                    if ( sessionFactory != null ) {
                        sessionFactory.close();
                    }
                    
                        if(lc.get(0) == null)
                        {
                           name = "img0.jpg"; 
                        }
                        else
                        {
                            name = "img" + lc.get(0) +".jpg";
                        }
                        
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        poids = (int) item.getSize();
                        
                        BufferedImage bi = javax.imageio.ImageIO.read(new File(UPLOAD_DIRECTORY + File.separator + name));
                        w = bi.getWidth();
                        h = bi.getHeight();
                    }
                    else
                    {
                        String fieldname = item.getFieldName();
                        
                        if (fieldname.equals("nomImg"))
                        {
                            nomImg = item.getString();
                        }
                        else if(fieldname.equals("catImg"))
                        {
                            categorieId = Integer.parseInt(item.getString());
                        }
                        else
                        {
                            desc = item.getString();
                        }                       
                    }
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
        
        SessionFactory sessionFactory;

                    // A SessionFactory is set up once for an application
                    sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                    // create a couple of events...
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();
                    
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int annee = calendar.get(Calendar.YEAR);
                    int mois = calendar.get(Calendar.MONTH);                
                    java.sql.Date aujourdhui = new java.sql.Date(annee-1900, mois, day);
                    
                    
                    HttpSession sessionUtilisateur = request.getSession();
                    out.println("user = " + sessionUtilisateur.getAttribute("sessionUtilisateur"));
                    
                    List<User> alC = new ArrayList<User>();
                    User propImg = null;
                    try
                    {
                        alC = session.createQuery( "from User where login='" + sessionUtilisateur.getAttribute("sessionUtilisateur") + "'").list();

                        for(User us : alC)
                        {
                            propImg = us;
                        }
                    } 
                    catch (NumberFormatException ex)
                    {
                        alC = null;
                    }
                    
                    
                    Creation newCreation = new Creation(nomImg, desc, "img/"+name, categorieId, propImg.getId(), aujourdhui, w+"x"+h, poids);
                    session.save(newCreation);

                    session.getTransaction().commit();
                    session.close();

                    if ( sessionFactory != null ) {
                        sessionFactory.close();
                    }
    
        //request.getRequestDispatcher("/index.jsp").forward(request, response);
        
        
        
        
     
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

