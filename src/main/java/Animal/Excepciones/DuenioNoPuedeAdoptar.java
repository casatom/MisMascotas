package Animal.Excepciones;

public class DuenioNoPuedeAdoptar extends RuntimeException {
  public DuenioNoPuedeAdoptar(String causa) {
    super("El duenio no puede adoptar a la mascota " + causa);
  }

}