/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Windows
 */
public class EJBUser {
            public static List<User> getUsers() {
        SessionFactory sessionFactory;

        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> listUser = new ArrayList<>();
        try {
            listUser = session.createQuery( "from User" ).list();
        } 
        catch (NumberFormatException ex) {
            listUser = null;
        }
        
        session.getTransaction().commit();
        session.close();
        
        sessionFactory.close();
        return listUser;
    }
}
