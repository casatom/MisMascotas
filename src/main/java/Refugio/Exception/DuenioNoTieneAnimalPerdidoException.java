package Refugio.Exception;

public class DuenioNoTieneAnimalPerdidoException extends RuntimeException{
  public DuenioNoTieneAnimalPerdidoException(String causa) {
    super("No tiene este animal perdido " + causa);
  }
}
