package db;

import Refugio.Lugar;
import Refugio.Refugio;
import Refugio.Rescatista;
import Refugio.TipoRefugios;

import java.sql.*;

public class RefugioDB {

  public static Integer crearRefugio(Refugio refugio) throws SQLException {
    Refugio creado = selectRefugio(refugio);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO refugio (direccion,tipoRefugio) values (?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, refugio.getDireccion());
      stm.setString(2, refugio.getClass().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        refugio.setId(id);
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


  public static Refugio selectRefugio(Refugio refugio) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_refugio,direccion,tipoRefugio from refugio WHERE id_refugio = '"+refugio.getId()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    Refugio refugioDB = null;

    if (resultSet.next()) {
      if(resultSet.getString("tipoRefugio").equals("RefugioAnimalesExoticos")){
        refugioDB = Refugio.createRefugio(resultSet.getString("direccion"),TipoRefugios.EXOTICOS);
      }
      if(resultSet.getString("tipoRefugio").equals("RefugioDePerros")){
        refugioDB = Refugio.createRefugio(resultSet.getString("direccion"),TipoRefugios.PERROS);
      }
      if(resultSet.getString("tipoRefugio").equals("RefugioDeGatos")){
        refugioDB = Refugio.createRefugio(resultSet.getString("direccion"),TipoRefugios.GATOS);
      }
      refugioDB.setId(resultSet.getInt("id_refugio"));
      return refugioDB;
    }

    connection.close();
    return null;
  }
}
