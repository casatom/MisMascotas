package db;


import Animal.Duenio;

import java.sql.*;

public class DuenioDB {
  public static Integer crearDuenio(Duenio duenio) throws SQLException {
    Duenio creado = DuenioDB.selectDuenio(duenio);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO dueño (nombreCompleto,nroDocumento,tipoDocumento,direccion,esValidoParaAdoptar,id_user) values (?,?,?,?,?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, duenio.getNombreCompleto());
      stm.setString(2, String.valueOf(duenio.getNumeroDocumento()));
      stm.setString(3, duenio.getTipoDocumento().toString());
      stm.setString(4,duenio.getDireccion());
      stm.setString(5,duenio.getEsValidoParaAdoptar().toString());
      stm.setString(6,duenio.getUsuario().getId().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        duenio.setId(id);
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


  public static Duenio selectDuenio(Duenio duenio) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_dueño,nombreCompleto,nroDocumento,tipoDocumento,direccion,esValidoParaAdoptar,id_user from dueño WHERE nombreCompleto = '"+duenio.getNombreCompleto()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    if (resultSet.next()) {
      return new Duenio(resultSet.getInt("id_consulta"));
    }

    connection.close();
    return null;
  }
}
