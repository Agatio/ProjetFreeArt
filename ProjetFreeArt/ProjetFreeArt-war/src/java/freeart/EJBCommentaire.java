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

public class EJBCommentaire {
        public static List<Commentaire> getCommentaires() {
        SessionFactory sessionFactory;

        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Commentaire> listCom = new ArrayList<>();
        try {
            listCom = session.createQuery( "from Commentaire" ).list();
        } 
        catch (NumberFormatException ex) {
            listCom = null;
        }
        
        session.getTransaction().commit();
        session.close();
        
        sessionFactory.close();
        return listCom;
    }
}
