package db;

import Animal.Duenio;
import Refugio.TipoRefugios;
import Refugio.Refugio;

import java.sql.*;

public class RefugioXDuenioDB {

  private Number id;
  private Duenio duenio;
  private Refugio refugio;

  public static Integer crearRefugioxDuenio(Duenio duenio, Refugio refugio) throws SQLException {
    RefugioXDuenioDB creado = selectRefugioXDuenio(duenio,refugio);

    if(creado == null){
      Connection connection = ConnectionDB.initDb();
      String sql = "INSERT INTO refugioxdueño (id_dueño,id_refugioxdueño) values (?,?)";
      assert connection != null;
      PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, duenio.getId().toString());
      stm.setString(2, refugio.getId().toString());

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

  public RefugioXDuenioDB(Duenio duenio, Refugio refugio) {
    this.duenio = duenio;
    this.refugio = refugio;
  }

  public Number getId() {
    return id;
  }

  public void setId(Number id) {
    this.id = id;
  }

  public static RefugioXDuenioDB selectRefugioXDuenio(Duenio duenio, Refugio refugio) throws SQLException {
    Connection connection = ConnectionDB.initDb();
    String sql = "select id_refugio,id_dueño,id_refugioxdueño from refugioxdueño WHERE id_dueño = '"+duenio.getId()+"' and id_refugio = '"+refugio.getId()+"'";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet resultRefugioXDuenio = stm.executeQuery();

    String sql2 = "select id_refugio,direccion,tipoRefugio from refugio WHERE id_refugio = '"+refugio.getId()+"'";
    PreparedStatement stm2 = connection.prepareStatement(sql2);
    ResultSet resultRefugio = stm.executeQuery();

    RefugioXDuenioDB refugioXDuenioDB = null;

    if (resultRefugioXDuenio.next()) {
      if(resultRefugio.getString("tipoRefugio").equals("")){
        return null;
      }
      if(resultRefugio.getString("tipoRefugio").equals("RefugioDePerros")){
        refugioXDuenioDB = new RefugioXDuenioDB(new Duenio(resultRefugioXDuenio.getInt("id_dueño")), Refugio.createRefugio(resultRefugio.getString("direccion"), TipoRefugios.PERROS));
      }
      if(resultRefugio.getString("tipoRefugio").equals("RefugioDeGatos")){
        refugioXDuenioDB = new RefugioXDuenioDB(new Duenio(resultRefugioXDuenio.getInt("id_dueño")), Refugio.createRefugio(resultRefugio.getString("direccion"), TipoRefugios.GATOS));
      }
      if(resultRefugio.getString("tipoRefugio").equals("RefugioAnimalesExoticos")){
        refugioXDuenioDB = new RefugioXDuenioDB(new Duenio(resultRefugioXDuenio.getInt("id_dueño")), Refugio.createRefugio(resultRefugio.getString("direccion"), TipoRefugios.EXOTICOS));
      }
      refugioXDuenioDB.setId(resultRefugioXDuenio.getInt("id_refugioxdueño"));
      return refugioXDuenioDB;
    }

    connection.close();
    return null;
  }
}
