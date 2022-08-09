package Animal;

import Animal.Animal;

public abstract class EstadoAnimal {

  private Animal animal;

  public EstadoAnimal(Animal animal) {
    this.animal = animal;
  }

  public abstract void validarCambio();
}
