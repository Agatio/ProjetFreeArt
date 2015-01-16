/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.EJBException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Agatio
 */
@Stateful
@LocalBean
public class Connexion implements SessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void ejbActivate() {

    }

    public void ejbPassivate() {
    }


    public void ejbRemove() {

    }



    public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {

    }



    public void ejbCreate() {

    }
    
    public static boolean checkConnexion(String login, String mdp){
        SessionFactory sessionFactory;

        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = null;
        try {
            List result = session.createQuery( "from User" ).list();
            for ( User u : (List<User>) result ) 
            {
                if(u.getLogin().equals(login) && u.getPassword().equals(mdp))
                {
                    user = u;
                }
            }
        } 
        catch (NumberFormatException ex) {
            user = null;
        }
        
        session.getTransaction().commit();
        session.close();
        
        
        
        sessionFactory.close();
        if (user != null) {
            return true;
        } else {
            return false;
        }
        
        
    }

}
