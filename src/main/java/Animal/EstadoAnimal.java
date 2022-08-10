package Animal;


public abstract class EstadoAnimal {

  private Animal animal;

  public EstadoAnimal(Animal animal) {
    this.animal = animal;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public abstract boolean validarCambio();
}
