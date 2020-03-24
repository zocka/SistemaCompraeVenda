
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static Connection con;
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sisven?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    // Classe de conexão com banco de dados
    public static Connection getInstance() {
     
        if (con == null) {
          try {
              Class.forName(DRIVER);
          } catch (ClassNotFoundException ex) {
              throw new RuntimeException("Erro no Driver: ", ex);
          }
          
          try {
              con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
              con.setAutoCommit(false);
          } catch (Exception ex) {
              throw new RuntimeException("Erro na conexão: ", ex);
          }
          
            System.out.println("Conexão Ok");
      } else {
      }
       return con;
    }

}
