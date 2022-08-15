package Animal;

public class AnimalExotico extends Animal {
  private AnimalExotico(String nombre) {
    super(nombre);
  }

  public static Animal createAnimal(String nombre){
    return new AnimalExotico(nombre);
  }
}
