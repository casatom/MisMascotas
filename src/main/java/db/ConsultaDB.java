package db;

import Animal.Animal;
import Refugio.Consulta;

import java.sql.*;

public class ConsultaDB {

  public static Integer crearConsulta(Consulta consulta) throws SQLException {
    Consulta creado = ConsultaDB.selectConsulta(consulta);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO consulta (fechaConsulta,id_veterinario,id_animal,id_dieta,observacion) values (?,?,?,?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, consulta.getFechaConsulta().toString());
      stm.setString(2, consulta.getVeterinario().getId().toString());
      stm.setString(3, consulta.getAnimal().getId().toString());
      stm.setString(4,consulta.getDietaRecomendada().getId().toString());
      stm.setString(5,consulta.getObservacion());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        consulta.setId(id);
        System.out.println("Consulta " + Integer.toString(id) + " creada con exito");
      }
      else {
        id = 0;
        System.out.println("error al crear Consulta");
      }
      connection.close();
      return id;
    }
    return 0;

  }


  public static Consulta selectConsulta(Consulta consulta) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_consulta,fechaConsulta,id_veterinario,id_animal,id_dieta,observacion from consulta where fechaConsulta = '"+consulta.getFechaConsulta().toString()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    if (resultSet.next()) {
      return new Consulta(resultSet.getInt("id_consulta"));
    }

    connection.close();
    return null;
  }
}
