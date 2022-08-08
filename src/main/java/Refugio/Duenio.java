package Refugio;

import Usuario.Usuario;

import java.util.ArrayList;

public class Duenio {
  private String nombreCompleto;
  private int numeroDocumento;
  private TipoDocumento tipoDocumento;
  private ArrayList<Animal> mascotas;
  private String direccion;
  private Boolean esValidoParaAdoptar
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
  }

  public void registrarMascota(){
    //TODO crear animal y asignarlo
    Animal animal = new Animal();
    this.mascotas.add(animal);
  }
}
