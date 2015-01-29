/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class CreationServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
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
            List<User> reqPropCreation = new ArrayList<User>();
            List<User> propCreation = new ArrayList<User>();
            List<Creation> file = new ArrayList<Creation>();
            try
            {
                alC = session.createQuery("from Creation").list();

                for (Creation crea : alC)
                {
                    file.add(crea);
                    reqPropCreation = session.createQuery("from User where id=" + crea.getIdUser()).list();

                    for (User nomProp : reqPropCreation)
                    {
                        propCreation.add(nomProp);
                    }
                }
            } catch (NumberFormatException ex)
            {
                alC = null;
            }

            request.setAttribute("categories", file);
            request.setAttribute("nomUtilisateur", propCreation);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else
        {
            if (action.equals("afficherParCat"))
            {
                List<User> reqPropCreation = new ArrayList<User>();
                List<User> propCreation = new ArrayList<User>();
                SessionFactory sessionFactory;
                sessionFactory = new Configuration()
                        .configure()
                        .buildSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                List<Categorie> listCate = new ArrayList<>();

                try
                {
                    listCate = session.createQuery("from Categorie").list();
                } catch (NumberFormatException ex)
                {
                    listCate = null;
                }

                List<Creation> listCreations = new ArrayList<>();

                try
                {
                    listCreations = session.createQuery("from Creation").list();

                    for (Creation crea : listCreations)
                    {
                        reqPropCreation = session.createQuery("from User where id=" + crea.getIdUser()).list();

                        for (User nomProp : reqPropCreation)
                        {
                            propCreation.add(nomProp);
                        }
                    }
                } catch (NumberFormatException ex)
                {
                    listCreations = null;
                }

                request.setAttribute("lesCategories", listCate);
                request.setAttribute("lesCreations", listCreations);
                request.setAttribute("nomUtilisateur", propCreation);

                RequestDispatcher rd = request
                        .getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
