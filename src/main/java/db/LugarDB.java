package db;

import Refugio.Lugar;
import Refugio.Rescatista;

import java.sql.*;

public class LugarDB {

  public static Integer crearLugar(Lugar lugar, Rescatista rescatista) throws SQLException {
    Lugar creado = selectLugar(lugar);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO lugar (lugar,id_rescatista) values (?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, lugar.getDireccion());
      stm.setString(2, rescatista.getId().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        lugar.setId(id);
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


  public static Lugar selectLugar(Lugar lugar) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_lugar,lugar,id_rescatista from lugar WHERE id_lugar = '"+lugar.getId()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();


    if (resultSet.next()) {
      return new Lugar(resultSet.getString("lugar"));
    }

    connection.close();
    return null;
  }
}
