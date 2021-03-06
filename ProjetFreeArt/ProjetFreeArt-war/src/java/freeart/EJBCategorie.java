/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Agatio
 */
@Stateful
@LocalBean
public class EJBCategorie {

    public static List<Categorie> getCategories() {
        SessionFactory sessionFactory;

        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Categorie> alC = new ArrayList<>();
        try {
            alC = session.createQuery( "from Categorie" ).list();
        } 
        catch (NumberFormatException ex) {
            alC = null;
        }
        
        session.getTransaction().commit();
        session.close();
        
        sessionFactory.close();
        return alC;
    }

}
