package Animal.Excepciones;

public class NoSePuedeCambiarEstado extends RuntimeException {
  public NoSePuedeCambiarEstado(String causa) {
    super("No se puede cambiar el estado porque " + causa);
  }
}
