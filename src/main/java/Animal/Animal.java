package Animal;

import Refugio.Consulta;
import Refugio.RepositorioAnimalesEnBusqueda;
import Refugio.RepositorioAnimalesSinDuenio;

import java.util.ArrayList;

public class Animal {
  private String nombre;
  private Duenio duenio;
  private EstadoAnimal estado;
  private ArrayList<Consulta> consultas;
  private Dieta tipoDieta;

  public Animal(String nombre) {
    this.nombre = nombre;
    this.estado = new SinDuenio(this);
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
  public boolean cambiarEstado(EstadoAnimal estado){
    if(estado.validarCambio()){
      this.estado = estado;
      return true;
    }
    return false;
  }

  public EstadoAnimal getEstado(){
    return this.estado;
  }

  public boolean establecerDuenio(Duenio duenio){
    if(duenio.esValidoParaAdoptar()){
      if(cambiarEstado(new ConDuenio(this))){
        this.duenio = duenio;
        return true;
      }
    }
    return false;
  }

  public void publicarExtravio(){
    RepositorioAnimalesEnBusqueda.getInstance().agregarAnimalEnBusqueda(this);
  }

  public void publicarAnimalEnAdopcion(){
    RepositorioAnimalesSinDuenio.getInstance().agregarAnimalSinDuenio(this);
  }
}
