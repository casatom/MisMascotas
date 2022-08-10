package Refugio;

import Animal.Animal;

import java.util.HashSet;
import java.util.Set;

public class RepositorioAnimalesSinDuenio {
  //TODO Singletone

  //////////////////////////////////  VARIABLES
  private static RepositorioAnimalesSinDuenio instance = null;
  private Set<Animal> animalesPublicados;


  //////////////////////////////////  CONSTRUCTORES
  private RepositorioAnimalesSinDuenio(){
    //todo traer todos los usuarios de la DB
    this.animalesPublicados = new HashSet<Animal>();
  }

  public static RepositorioAnimalesSinDuenio getInstance(){
    if(instance == null){
      instance = new RepositorioAnimalesSinDuenio();
    }
    return instance;
  }


  public void agregarAnimalSinDuenio(Animal animal){
    this.animalesPublicados.add(animal);
  }

  public void eliminarAnimalPublicado(Animal animal){
    this.animalesPublicados.remove(animal);
  }

  public boolean isPublicado(Animal animal){
    return this.animalesPublicados.stream().anyMatch(a-> a.equals(animal));
  }
}
