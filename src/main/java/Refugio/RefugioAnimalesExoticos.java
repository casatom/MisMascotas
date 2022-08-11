package Refugio;

import Animal.AnimalExotico;

import java.util.Set;

public class RefugioAnimalesExoticos extends Refugio{

  private Set<AnimalExotico> animalExoticos;

  public RefugioAnimalesExoticos(String direccion) {
    super(direccion);
  }

  public void agregarAnimalExotico(AnimalExotico animalExotico){
    animalExotico.subcribirAunRefugio(this);
    this.animalExoticos.add(animalExotico);
  }

  @Override
  public int cantidadAnimales() {
    return this.animalExoticos.size();
  }
}
