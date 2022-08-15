package Animal;


public class Perro extends Animal {
  private Perro(String nombre) {
    super(nombre);
  }

  public static Animal createAnimal(String nombre){
    return new Perro(nombre);
  }
}
