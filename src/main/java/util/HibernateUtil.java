package util;

import dao.ConexaoHibernate;
import org.hibernate.SessionFactory;

public final class HibernateUtil {
    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return ConexaoHibernate.getSessionFactory();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
