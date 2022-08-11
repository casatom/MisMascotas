package Animal.Excepciones;

public class AnimalNoCreadoException extends RuntimeException {
  public AnimalNoCreadoException(String causa) {
    super("ya esta subcripto porque " + causa);
  }
}
