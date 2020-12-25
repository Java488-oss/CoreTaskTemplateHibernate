package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection connection = null;
    private static SessionFactory sessionFactory;

    public static Connection getConnectionJDBC() {
        try {
            connection= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

//    public static void main(String[] args) throws SQLException {
//        System.out.println(getSessionHibernate().isClosed());
//        getSessionHibernate().close();
//
//    }

    public static SessionFactory getSessionHibernate() {
        if (sessionFactory == null) {
            Configuration cfg = new Configuration()
                    .addAnnotatedClass(User.class)
                    //.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
                    .setProperty("hibernate.connection.url", DB_URL)
                    .setProperty("hibernate.connection.username", DB_USERNAME)
                    .setProperty("hibernate.connection.password", DB_PASSWORD)
                    .setProperty("show_sql", "true")
                    .setProperty("hibernate.c3p0.min_size", "5")
                    .setProperty("hibernate.c3p0.max_size", "20");

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(builder.build());

        }
        return sessionFactory;
    }


}
