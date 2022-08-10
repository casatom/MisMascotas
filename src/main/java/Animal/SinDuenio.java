package Animal;

import Animal.Animal;

public class SinDuenio extends EstadoAnimal {


  public SinDuenio(Animal animal) {
    super(animal);
    if(validarCambio()){
      this.getAnimal().publicarAnimalEnAdopcion();
    }
  }

  @Override
  public boolean validarCambio() {
    //TODO testear estas validaciones

    if(this.getAnimal().getEstado().getClass() == this.getClass()){
      return true;
    }
    //Si viene de un estado EnBusqueda o ConDuenio no es posible
    return false;
  }
}
