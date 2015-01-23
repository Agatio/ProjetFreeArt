/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Agatio
 */
public class CreationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally { 
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String action = request.getParameter("action");
        
        if (action.equals("testNath"))
        {
            SessionFactory sessionFactory;

            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Creation> alC = new ArrayList<>();
            List<String> noms = new ArrayList<String>();
            List<String> file = new ArrayList<String>();
            try
            {
                alC = session.createQuery( "from Creation" ).list();

                for(Creation crea : alC)
                {
                    noms.add(crea.getNom());
                    file.add(crea.getFile());
                }
            } 
            catch (NumberFormatException ex)
            {
                alC = null;
            }

                request.setAttribute("categories", file);

                RequestDispatcher rd = request
                                .getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
        }
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
    
    public static /*List<Creation>*/void getCreations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        List<Creation> resCrea = freeart.EJBCreation.getCreations();
        Vector<String> noms = new Vector<String>();
        Vector<String> file = new Vector<String>();
        for(Creation crea : resCrea)
        {
            noms.add(crea.getNom());
            noms.add(crea.getFile());
        }
        
        System.out.println("<p>testest</p>");
        
        request.setAttribute("listcrea", resCrea);
        //return resCrea;
    }
}
