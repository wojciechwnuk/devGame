package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }


    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
