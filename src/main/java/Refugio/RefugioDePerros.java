package Refugio;

import Animal.Perro;

import java.util.Set;

public class RefugioDePerros extends Refugio{

  private Set<Perro> perros;

  private RefugioDePerros(String direccion) {
    super(direccion);
  }

  public static Refugio createRefugio(String direccion) {
    return new RefugioDePerros(direccion);
  }

  public void agregarAnimal(Perro perro){
    perro.subcribirAunRefugio(this);
    this.perros.add(perro);
  }

  @Override
  public int cantidadAnimales() {
    return this.perros.size();
  }
}
