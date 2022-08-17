package db;


import Refugio.Refugio;
import Refugio.Veterinario;

import java.sql.*;

public class VeterinarioDB {

  public static Integer crearVeterinario(Veterinario veterinario, Refugio refugio) throws SQLException {
    Veterinario creado = selectVeterinario(veterinario);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO veterinario (nombre,cuit,id_refugio) values (?,?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, veterinario.getNombreCompleto());
      stm.setString(2, veterinario.getCuit());
      stm.setString(3, refugio.getId().toString());
      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        veterinario.setId(id);
        System.out.println("veterinario " + Integer.toString(id) + " creada con exito");
      }
      else {
        id = 0;
        System.out.println("error al crear el veterinario");
      }
      connection.close();
      return id;
    }
    return 0;

  }


  public static Veterinario selectVeterinario(Veterinario veterinario) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_veterinario,nombre,cuit,id_refugio from veterinario WHERE id_veterinario = '"+veterinario.getId()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    Veterinario veterinarioDB = null;

    if (resultSet.next()) {
      veterinarioDB = new Veterinario(resultSet.getString("nombre"),resultSet.getString("cuit"));
      veterinarioDB.setId(resultSet.getInt("id_veterinario"));
      return veterinarioDB;
    }

    connection.close();
    return null;
  }
}
