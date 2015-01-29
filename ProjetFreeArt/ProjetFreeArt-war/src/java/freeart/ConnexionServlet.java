/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Agatio
 */
public class ConnexionServlet extends HttpServlet
{

    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action.equals("loginform"))
        {
            boolean resCo = freeart.Connexion.checkConnexion(request.getParameter("login"), request.getParameter("password"));
            if (resCo == true)
            {
                String url = request.getRequestURL().toString();
                String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
                out.println("<p>Connexion reussie</p>");

                User utilis = new User(request.getParameter("login"), request.getParameter("password"));
                HttpSession session = request.getSession();
                session.setAttribute(ATT_SESSION_USER, request.getParameter("login"));

                response.sendRedirect("index.jsp");
            } else
            {
                out.println("<p>Connexion impossible</p>");
            }
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
