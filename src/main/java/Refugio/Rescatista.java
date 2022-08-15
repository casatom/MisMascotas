package Refugio;

import Animal.Animal;
import Animal.TipoAnimales;
import Animal.SinDuenio;
import Animal.ConDuenio;
import Animal.Duenio;
import Refugio.Exception.DuenioNoTieneAnimalPerdidoException;

import java.util.ArrayList;

public class Rescatista {

  private String nombre;
  private ArrayList<Lugar> listaLugares;

  public Rescatista(String nombre) {
    this.nombre = nombre;
    this.listaLugares = new ArrayList<>();
  }

  public Animal buscarAnimal(String nombre, TipoAnimales tipoAnimal){
    Animal animalEncontrado = Animal.createAnimal(tipoAnimal,nombre);
    animalEncontrado.cambiarEstado(new SinDuenio(animalEncontrado));
    return animalEncontrado;
  }

  public void devolverAnimal(Duenio duenio, Animal animalEncontrado){
    Animal animalPerdido = duenio.getAnimalPerdido(animalEncontrado.getNombre());

    if(animalPerdido == null){
      throw new DuenioNoTieneAnimalPerdidoException("No coicide el nombre del animal");
    }

    if(RepositorioAnimalesEnBusqueda.getInstance().isEnBusqueda(animalPerdido)){
      animalPerdido.cambiarEstado(new ConDuenio(animalPerdido));
    }

    if(RepositorioAnimalesSinDuenio.getInstance().isPublicado(animalEncontrado)){
      RepositorioAnimalesSinDuenio.getInstance().eliminarAnimalPublicado(animalEncontrado);
    }

  }

  public void getLugaresDeBusqueda(int cantidadLugares){
    //TODO buscar en la API los lugares que figuran y llenar la lista
    this.listaLugares.addAll(ApiMascotasEncontradas.getInstance().requestLugares(cantidadLugares));

  }

}
