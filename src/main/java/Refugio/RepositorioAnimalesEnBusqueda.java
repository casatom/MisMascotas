package Refugio;

import Animal.Animal;
import Animal.ConDuenio;

import java.util.HashSet;
import java.util.Set;

public class RepositorioAnimalesEnBusqueda {
  //TODO Singletone
  //////////////////////////////////  VARIABLES
  private static RepositorioAnimalesEnBusqueda instance = null;
  private Set<Animal> animalesEnBusqueda;


  //////////////////////////////////  CONSTRUCTORES
  private RepositorioAnimalesEnBusqueda(){
    //todo traer todos los usuarios de la DB
    this.animalesEnBusqueda = new HashSet<Animal>();
  }

  public static RepositorioAnimalesEnBusqueda getInstance(){
    if(instance == null){
      instance = new RepositorioAnimalesEnBusqueda();
    }
    return instance;
  }

  public void animalEncontrado(Animal animal){
    if(animal.cambiarEstado(new ConDuenio(animal))){
      this.animalesEnBusqueda.remove(animal);
    }
  }

  public void agregarAnimalEnBusqueda(Animal animal){
    this.animalesEnBusqueda.add(animal);
  }

  public boolean isEnBusqueda(Animal animal){
    return this.animalesEnBusqueda.stream().anyMatch(a-> a.equals(animal));
  }

}
