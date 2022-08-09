package Animal;

import Refugio.Consulta;

import java.util.ArrayList;

public class Animal {
  private String nombre;
  private Duenio duenio;
  private EstadoAnimal estado;
  private ArrayList<Consulta> consultas;
  private Dieta tipoDieta;

  public Animal(String nombre, EstadoAnimal estado) {
    this.nombre = nombre;
    this.estado = estado;
    this.consultas = new ArrayList<>();
    this.duenio = null;
    this.tipoDieta = null;
  }

  public void setDuenio(Duenio duenio) {
    this.duenio = duenio;
  }

  public void setTipoDieta(Dieta dieta){
    this.tipoDieta = dieta;
  }

  public String getAlimentacion(){
    return this.tipoDieta.getAlimentacion();
  }

  //Cada estado manejara el cambio y su validacion
  public void cambiarEstado(EstadoAnimal estado){
    this.estado = estado;
  }

  public EstadoAnimal getEstado(){
    return this.estado;
  }

  public void establecerDuenio(Duenio duenio){
    this.duenio = duenio;
  }
}
