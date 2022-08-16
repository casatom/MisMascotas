package db;

import Animal.Animal;
import Animal.TipoAnimales;

import java.sql.*;

public class AnimalDB {

  public static Integer crearAnimal(Animal animal) throws SQLException {
    Animal creado = AnimalDB.selectAnimal(animal);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO animal (nombre,id_dueño,estado,id_dieta,tipoAnimal,id_refugio) values (?,?,?,?,?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, animal.getNombre());
      stm.setString(2, animal.getDuenio().getId().toString());
      stm.setString(3,animal.getEstado().getClass().toString());
      stm.setString(4,animal.getTipoDieta().getId().toString());
      stm.setString(5,animal.getClass().toString());
      stm.setString(6,animal.getRefugioRelacionado().getId().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        animal.setId(id);
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


  public static Animal selectAnimal(Animal animal) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_animal,nombre,id_dueño,estado,id_dieta,tipoAnimal,id_refugio from animal where nombre =  '"+animal.getNombre()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    if (resultSet.next()) {
      Animal creado = Animal.createAnimal(TipoAnimales.valueOf(resultSet.getString("tipoAnimal")) ,resultSet.getString("nombre"));
      creado.setId(resultSet.getInt("id_animal"));
      return creado;
    }

    connection.close();
    return null;
  }

}
