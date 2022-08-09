package Animal;

public abstract class Dieta {
  private String observacion;

  public Dieta(String observacion) {
    this.observacion = observacion;
  }

  public abstract String getAlimentacion();
}
