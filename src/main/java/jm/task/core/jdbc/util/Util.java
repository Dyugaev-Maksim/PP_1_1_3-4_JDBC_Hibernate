package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

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


}


