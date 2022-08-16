package db;


import Animal.Duenio;
import Usuario.Usuario;
import Usuario.Admin;

import java.sql.*;

public class UsuarioDB  {
  public static Integer crearUsuario(Usuario usuario) throws SQLException {
    Usuario creado = UsuarioDB.selectUsuario(usuario);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO usuario (mail,username,salt,validado,contraHasheada,esAdmin) values (?,?,?,?,?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, usuario.getEmail());
      stm.setString(2, usuario.getUsername());
      stm.setString(3, usuario.getSalt());
      stm.setString(4,usuario.getValidado().toString());
      stm.setString(5,usuario.getContraHasheada());
      stm.setString(6,usuario.isEsAdmin().toString());

      stm.executeUpdate();

      // obtener último id generado
      ResultSet generatedKeys = stm.getGeneratedKeys();
      int id = -1;
      if (generatedKeys.next()) {
        id = generatedKeys.getInt(1);
        usuario.setId(id);
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


  public static Usuario selectUsuario(Usuario usuario) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_user,mail,username,salt,validado,contraHasheada,esAdmin from usuario WHERE username = '"+usuario.getUsername()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultSet = stm.executeQuery();

    if (resultSet.next()) {
      if(resultSet.getString("esAdmin").equals("true"))
        return new Admin(resultSet.getInt("id_user"));
      else
        return new Usuario(resultSet.getInt("id_user"));
    }

    connection.close();
    return null;
  }
}
