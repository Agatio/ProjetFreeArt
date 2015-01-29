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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Nathalie
 */
@WebServlet(name = "AfficheDetailImg", urlPatterns ={"/AfficheDetailImg"})
public class AfficheDetailImgServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        request.setAttribute("chemin", request.getParameter("chemin"));

        List<Creation> alC = new ArrayList<>();
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        alC = session.createQuery("from Creation").list();

        List<User> reqPropCreation = new ArrayList<User>();

        for (Creation crea : alC)
        {
            if (crea.getFile().equals(request.getParameter("chemin")))
            {
                request.setAttribute("creationDetail", crea);
                reqPropCreation = session.createQuery("from User where id=" + crea.getIdUser()).list();

                for (User nomProp : reqPropCreation)
                {
                    request.setAttribute("nomUtilisateur", nomProp);
                }
            }
        }

        RequestDispatcher rd = request
                .getRequestDispatcher("/detailImg.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
