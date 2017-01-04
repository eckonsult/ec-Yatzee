package org.hibernate.util;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory = buildSessionFactory();
/**
 *  Returns hibernate sessionFactory based on setting hibernate.cfg.xml and adds
 *  annotated class Player to configuration
 * @return SessionFactory
 */
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(org.hibernate.model.Player.class);
            return cfg.configure().buildSessionFactory();
        
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
}
