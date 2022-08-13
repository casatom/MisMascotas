package Animal;

import Animal.Excepciones.DuenioNoPuedeAdoptar;
import Refugio.Refugio;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Duenio {
  private String nombreCompleto;
  private int numeroDocumento;
  private TipoDocumento tipoDocumento;
  private ArrayList<Animal> mascotas;
  private String direccion;
  private Boolean esValidoParaAdoptar;
  private Usuario usuario;
  private Set<Refugio> refugios;

  //TODO Agregar refugios al duenio

  public Duenio(String nombreCompleto, int numeroDocumento, TipoDocumento tipoDocumento, String direccion, Usuario usuario) {
    this.nombreCompleto = nombreCompleto;
    this.numeroDocumento = numeroDocumento;
    this.tipoDocumento = tipoDocumento;
    this.direccion = direccion;
    this.usuario = usuario;
    this.esValidoParaAdoptar = false;
    this.mascotas = new ArrayList<>();
    this.refugios = new HashSet<>();
  }

  public Boolean esValidoParaAdoptar() {
    return esValidoParaAdoptar;
  }

  public Set<Refugio> getRefugios() {
    return refugios;
  }

  public void agregarRefugio(Refugio refugio){
    this.refugios.add(refugio);
  }

  public void setEsValidoParaAdoptar(Boolean esValidoParaAdoptar) {
    this.esValidoParaAdoptar = esValidoParaAdoptar;
  }

  public void adoptarMascota(Animal animal){
    if(this.esValidoParaAdoptar){
      this.mascotas.add(animal);
    }
    else
      throw new DuenioNoPuedeAdoptar("no esta validado para adoptar");
  }


  public Animal getAnimalPerdido(String nombreAnimal){
    return this.mascotas.stream().filter(m -> m.getNombre().equals(nombreAnimal)).findFirst().orElse(null);
  }

  public void registrarMascota(String nombre, TipoAnimales tipoAnimales){
    //TODO testear
    Animal animal = Animal.createAnimal(tipoAnimales,nombre);
    if(animal.establecerDuenio(this)){
      this.mascotas.add(animal);
    }
    else
      throw new DuenioNoPuedeAdoptar("No esta validado o la mascota no puede cambiar de estado");
  }
}
