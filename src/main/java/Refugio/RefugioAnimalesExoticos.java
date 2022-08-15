package Refugio;

import Animal.AnimalExotico;

import java.util.Set;

public class RefugioAnimalesExoticos extends Refugio{

  private Set<AnimalExotico> animalExoticos;

  private RefugioAnimalesExoticos(String direccion) {
    super(direccion);
  }

  public static Refugio createRefugio(String direccion) {
    return new RefugioAnimalesExoticos(direccion);
  }

  public void agregarAnimal(AnimalExotico animalExotico){
    animalExotico.subcribirAunRefugio(this);
    this.animalExoticos.add(animalExotico);
  }

  @Override
  public int cantidadAnimales() {
    return this.animalExoticos.size();
  }
}
