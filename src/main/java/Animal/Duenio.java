package Animal;

import Animal.Excepciones.DuenioNoPuedeAdoptar;
import Usuario.Usuario;

import java.util.ArrayList;

public class Duenio {
  private String nombreCompleto;
  private int numeroDocumento;
  private TipoDocumento tipoDocumento;
  private ArrayList<Animal> mascotas;
  private String direccion;
  private Boolean esValidoParaAdoptar;
  private Usuario usuario;

  public Duenio(String nombreCompleto, int numeroDocumento, TipoDocumento tipoDocumento, String direccion, Usuario usuario, Boolean esValidoParaAdoptar) {
    this.nombreCompleto = nombreCompleto;
    this.numeroDocumento = numeroDocumento;
    this.tipoDocumento = tipoDocumento;
    this.direccion = direccion;
    this.usuario = usuario;
    this.esValidoParaAdoptar = esValidoParaAdoptar;
    this.mascotas = new ArrayList<>();
  }


  public void adoptarMascota(Animal animal){
    if(this.esValidoParaAdoptar){
      this.mascotas.add(animal);
    }
    else
      throw new DuenioNoPuedeAdoptar("no esta validado para adoptar");
  }

  public void registrarMascota(String nombre){
    //TODO crear animal y asignarlo
    Animal animal = new Animal(nombre,null);
    animal.setDuenio(this);
    this.mascotas.add(animal);
  }
}
