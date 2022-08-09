package Refugio;

import Animal.Perro;

import java.util.Set;

public class RefugioDePerros extends Refugio{

  private Set<Perro> perros;

  public void agregarPerro(Perro perro){
    this.perros.add(perro);
  }

  @Override
  public int cantidadAnimales() {
    return this.perros.size();
  }
}
