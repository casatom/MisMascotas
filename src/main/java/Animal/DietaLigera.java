package Animal;

public class DietaLigera extends Dieta{
  public DietaLigera(String observacion) {
    super(observacion);
  }

  public DietaLigera() {
    super(null);
  }

  @Override
  public String getAlimentacion() {
    //TODO Llenar dieta Ligera
    //Esta configuracion de la dieta podria ser guardada en la DB
    String alimentacionLigera = "La dieta Ligera contiene\n";
    return alimentacionLigera + this.getObservacion();
  }
}
