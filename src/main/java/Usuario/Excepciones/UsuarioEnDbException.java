package Usuario.Excepciones;

public class UsuarioEnDbException extends RuntimeException{
  public UsuarioEnDbException(String causa) {
    super("El usuario es no se pudo guardar porque " + causa);
  }
}
