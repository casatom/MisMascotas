package db;

import Refugio.Refugio;
import Refugio.Rescatista;

import java.sql.*;

public class RescatistaDB {
  public static Integer crearRescatista(Rescatista rescatista, Refugio refugio) throws SQLException {
    Rescatista creado = selectRescatista(rescatista);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO rescatista (nombre,id_refugio) values (?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, rescatista.getNombre());
      stm.setString(2, refugio.getId().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        rescatista.setId(id);
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


  public static Rescatista selectRescatista(Rescatista rescatista) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_rescatista,nombre,id_refugio from rescatista WHERE id_rescatista = '"+rescatista.getId()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();


    if (resultSet.next()) {
      Rescatista rescatistaDB = new Rescatista(resultSet.getString("nombre"));
      rescatistaDB.setId(resultSet.getInt("id_rescatista"));
      return rescatistaDB;
    }

    connection.close();
    return null;
  }

}
