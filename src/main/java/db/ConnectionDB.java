package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

  public static Connection initDb() throws SQLException {
    try {
      String jdbcURL = "jdbc:mysql://localhost:3306/bd_mismascotas";
      String username = "root";
      String password = "123456";
      Connection connection = DriverManager.getConnection(jdbcURL, username, password);
      return connection;
    } catch (Error error) {
      System.out.println(error);
    }
    return null;
  }



}