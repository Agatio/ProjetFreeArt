package freeart;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("debut");
        SessionFactory sessionFactory;

        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Creation> resCrea = freeart.EJBCreation.getCreations();
        try {
            System.out.println("Je suis là");
            System.out.println("alC = " + resCrea.get(0).getNom());
            
            for(Creation crea : resCrea)
            {
                System.out.println(crea.getNom());
            }
        } 
        catch (NumberFormatException ex) {
            System.out.println("je suis pas content");
        }
        
        session.getTransaction().commit();
        session.close();        
        sessionFactory.close();
    }
}
