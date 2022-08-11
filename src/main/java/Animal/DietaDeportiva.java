package Animal;

public class DietaDeportiva extends Dieta{
  public DietaDeportiva(String observacion) {
    super(observacion);
  }

  public DietaDeportiva() {
    super(null);
  }

  @Override
  public String getAlimentacion() {
    //TODO Llenar dieta Deportiva
    //Esta configuracion de la dieta podria ser guardada en la DB
    String alimentacionDeportiva = "La dieta Deportiva contiene\n";
    return alimentacionDeportiva + this.getObservacion();
  }
}
