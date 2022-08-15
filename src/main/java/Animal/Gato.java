package Animal;


public class Gato extends Animal {
  private Gato(String nombre) {
    super(nombre);
  }

  public static Animal createAnimal(String nombre){
    return new Gato(nombre);
  }
}
