package Refugio;

import Animal.AnimalExotico;

import java.util.Set;

public class RefugioAnimalesExoticos extends Refugio{

  private Set<AnimalExotico> animalExoticos;

  public void agregarAnimalExotico(AnimalExotico animalExotico){
    this.animalExoticos.add(animalExotico);
  }

  @Override
  public int cantidadAnimales() {
    return this.animalExoticos.size();
  }
}
