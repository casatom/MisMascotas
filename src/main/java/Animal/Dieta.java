package Animal;

public abstract class Dieta {
  private String observacion;
  private Number id;

  public Dieta(String observacion) {
    this.observacion = "La observacion de la Dieta es:" + observacion;
  }

  public abstract String getAlimentacion();

  public Number getId() {
    return id;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }
}
