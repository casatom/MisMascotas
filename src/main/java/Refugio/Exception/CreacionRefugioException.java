package Refugio.Exception;

public class CreacionRefugioException extends RuntimeException {
  public CreacionRefugioException(String causa) {
    super("ya esta subcripto porque " + causa);
  }
}
