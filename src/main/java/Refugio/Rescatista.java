package Refugio;

import Animal.Animal;

import java.util.ArrayList;

public class Rescatista {

  private String nombre;
  private ArrayList<String> listaLugares;

  public Rescatista(String nombre) {
    this.nombre = nombre;
    this.listaLugares = new ArrayList<>();
  }

  public Animal buscarAnimal(){
    //TODO deberia buscar el lugar y crear un animal cuando lo encuentra
    return null;
  }

  public void lugaresDeExtravio(){
    //TODO buscar en la API los lugares que figuran y llenar la lista

  }

}
