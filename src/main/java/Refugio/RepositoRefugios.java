package Refugio;

import Animal.Animal;

import java.util.HashSet;
import java.util.Set;

public class RepositoRefugios {
  //TODO Singletone
  //////////////////////////////////  VARIABLES
  private String nombre;
  private static RepositoRefugios instance = null;
  private Set<Refugio> refugios;


  //////////////////////////////////  CONSTRUCTORES
  private RepositoRefugios(){
    //todo traer todos los usuarios de la DB
    this.refugios = new HashSet<Refugio>();
  }

  public static RepositoRefugios getInstance(){
    if(instance == null){
      instance = new RepositoRefugios();
    }
    return instance;
  }

  public void guardarRefugio(Refugio refugio){
    this.refugios.add(refugio);
  }

  public Refugio crearRefugio(String nombre, TipoRefugios tipoRefugio){
    Refugio refugio = Refugio.createRefugio(nombre,tipoRefugio);
    this.refugios.add(refugio);
    return refugio;
  }

  public boolean existeElRefugio(Refugio refugio){
    return this.refugios.stream().anyMatch(a-> a.equals(refugio));
  }
}
