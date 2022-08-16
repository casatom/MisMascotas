package db;

import Animal.*;
import Refugio.Consulta;

import java.sql.*;

public class DietaDB {

  public static Integer crearDieta(Dieta dieta, Animal animal, Consulta consulta) throws SQLException {
    Dieta creado = selectDieta(dieta);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO dieta (observacionDieta,id_animal,dietaRecomendada,id_consulta) values (?,?,?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, dieta.getObservacion());
      stm.setString(2, animal.getId().toString());
      stm.setString(3, dieta.getClass().toString());
      stm.setString(4, consulta.getId().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        dieta.setId(id);
        System.out.println("animal " + Integer.toString(id) + " creada con exito");
      }
      else {
        id = 0;
        System.out.println("error al crear el animal");
      }
      connection.close();
      return id;
    }
    return 0;

  }


  public static Dieta selectDieta(Dieta dieta) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_dieta,observacionDieta,id_animal,dietaRecomendada from dieta WHERE id_dieta = '"+dieta.getId()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    Dieta dietaDB = null;

    if (resultSet.next()) {
      if(resultSet.getString("dietaRecomendada").equals("DietaLigera")){
        dietaDB = new DietaLigera();
      }
      if(resultSet.getString("dietaRecomendada").equals("DietaDeportiva")){
        dietaDB = new DietaDeportiva();
      }
      if(resultSet.getString("dietaRecomendada").equals("DietaBalanceada")){
        dietaDB = new DietaBalanceada();
      }
      dieta.setId(resultSet.getInt("id_dieta"));
      return dietaDB;
    }

    connection.close();
    return null;
  }
}
