package Usuario;

import Usuario.Excepciones.ContraseniaEsInvalidaException;
import Usuario.Excepciones.UsuarioEnDbException;
import Usuario.Excepciones.UsuarioException;

import java.util.ArrayList;

public class RepositorioUsuarios {
  //////////////////////////////////  VARIABLES
  private static RepositorioUsuarios instance = null;
  private ArrayList<Usuario> usuarios;


  //////////////////////////////////  CONSTRUCTORES
  private RepositorioUsuarios(){
    //todo traer todos los usuarios de la DB
    this.usuarios = new ArrayList<>();
  }

  public static RepositorioUsuarios getInstance(){
    if(instance == null){
      instance = new RepositorioUsuarios();
    }
    return instance;
  }

  //////////////////////////////////  GETTERS
  public ArrayList<Usuario> getUsuarios() {
    return usuarios;
  }

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE
  public boolean validarUsuario(Usuario usuario, Boolean validacion){
    Usuario usuarioAValidar = this.usuarios.stream().filter(u -> u.equals(usuario)).findFirst().orElse(null);
    if(usuarioAValidar == null){
      return false;
    }
    usuarioAValidar.setValidado(validacion);
    return true;
  }

  public boolean validarUsuario(String username, Boolean validacion){
    Usuario usuarioAValidar = this.usuarios.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    if(usuarioAValidar == null){
      return false;
    }
    usuarioAValidar.setValidado(validacion);
    return true;
  }

  public Usuario accesoAlSistema(String username, String contra){
    Usuario usuarioConUsername = this.usuarios.stream().filter(u-> u.getUsername().equals(username)).findFirst().orElse(null);
    //Si no encuentra el usuario por username
    if(usuarioConUsername == null){
      return null;
    }
    //Si el usuario esta validado
    if(!usuarioConUsername.isValido()){
      return null;
    }
    //Si el intento de contrasenia es fallido se devolvera nulo
    // lo pongo en los dos lados por si la contrasenia es correcta pero no paso el tiempo del ultimo acceso
    if(usuarioConUsername.isColision(contra)){
     return usuarioConUsername;
    }
    throw new ContraseniaEsInvalidaException("la contrasenia no es la misma a la del usuario");
  }

  public Usuario crearUsuario(String username, String email, String contra){
    try{
      Usuario usuarioNuevo = new Usuario(username,email,contra,false);
      //agregarUsuario(usuarioNuevo);
      return usuarioNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }

  private Usuario usuarioYaCreado(Usuario usuario){
    if(this.usuarios.stream().filter(u -> u.getUsername().equals(usuario.getUsername())).findFirst().orElse(null) ==null){
      return null;
    }
    else
      throw new UsuarioEnDbException("El username ya existe");

  }

  public void agregarUsuario(Usuario usuario){
    if(usuarioYaCreado(usuario)==null){
      this.usuarios.add(usuario);
    }
  }

  public Admin crearAdmin(String username, String email, String contra){
    try{
      Admin adminNuevo = new Admin(username,email,contra,true);
      this.usuarios.add(adminNuevo);
      return adminNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }


}
