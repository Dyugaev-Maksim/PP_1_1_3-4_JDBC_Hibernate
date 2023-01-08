package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory;

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getDBConnection() {

        {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            } catch (SQLException e) {
                System.out.println("Не удалось загрузить класс драйвера!");
            }
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb");
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
              StandardServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
                      .applySettings(configuration.getProperties()).build();
              sessionFactory=configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Соединения с базой нет");
            }
        }
        return sessionFactory;
    }

}


