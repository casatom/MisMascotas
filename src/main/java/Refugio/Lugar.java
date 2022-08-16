package Refugio;

public class Lugar {
  private Number id;
  private String direccion;

  public Lugar(String direccion) {
    this.direccion = direccion;
  }

  public Number getId() {
    return id;
  }

  public void setId(Number id) {
    this.id = id;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
}
