package Interfaz;

import Animal.Animal;
import Animal.Duenio;
import Animal.TipoDocumento;
import Animal.TipoAnimales;
import Refugio.Refugio;
import Refugio.TipoRefugios;
import Refugio.RepositoRefugios;
import Usuario.Usuario;
import Usuario.RepositorioUsuarios;

public class InterfazUsuario {

  public Usuario registrarUsuario(String username, String contra, String email){
    return RepositorioUsuarios.getInstance().crearUsuario(username,email,contra);
  }

  public Usuario accesoDelUsuario(String username, String contra){
    return RepositorioUsuarios.getInstance().accesoAlSistema(username, contra);
  }

  public Duenio crearDuenio(String nombreCompleto, int numeroDocumento, TipoDocumento tipoDocumento, String direccion, Usuario usuario){
    return new Duenio(nombreCompleto, numeroDocumento, tipoDocumento, direccion, usuario);
  }

  public void duenioAdoptaMascota(Duenio duenio, Animal animal){
    duenio.adoptarMascota(animal);
  }

  public void duenioRegistraMascota(Duenio duenio, String nombre, TipoAnimales tipoAnimales){
    duenio.registrarMascota(nombre,tipoAnimales);
  }

  public Refugio registrarRefugio(String direccion, TipoRefugios tipoRefugio){
    return RepositoRefugios.getInstance().crearRefugio(direccion,tipoRefugio);
  }

  public void cambiarNombreRefugio(String nombre){
    RepositoRefugios.getInstance().setNombre(nombre);
  }


}
