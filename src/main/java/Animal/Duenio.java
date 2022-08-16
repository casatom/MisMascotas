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
  private Number id;

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

  public Duenio(Number id) {
    this.id = id;
  }

  public Number getId() {
    return id;
  }

  public void setId(Number id) {
    this.id = id;
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
      if(animal.establecerDuenio(this)){
        agregarAnimal(animal);
      }
    }
    else
      throw new DuenioNoPuedeAdoptar("no esta validado para adoptar");
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public int getNumeroDocumento() {
    return numeroDocumento;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public String getDireccion() {
    return direccion;
  }

  public Boolean getEsValidoParaAdoptar() {
    return esValidoParaAdoptar;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  private void agregarAnimal(Animal animal) {
    this.mascotas.add(animal);
  }

  public void animalPerdido(Animal animal){
    if(esMascotaPropia(animal)){
      animal.cambiarEstado(new EnBusqueda(animal));
    }
  }

  private boolean esMascotaPropia(Animal animal) {
    return this.mascotas.contains(animal);
  }


  public Animal getAnimalPerdido(String nombreAnimal){
    return this.mascotas.stream().filter(m -> m.getNombre().equals(nombreAnimal)).findFirst().orElse(null);
  }

  public void registrarMascota(String nombre, TipoAnimales tipoAnimales){
    //TODO testear
    Animal animal = Animal.createAnimal(tipoAnimales,nombre);
    if(animal.establecerDuenio(this)){
      agregarAnimal(animal);
    }
    else
      throw new DuenioNoPuedeAdoptar("No esta validado o la mascota no puede cambiar de estado");
  }
}
