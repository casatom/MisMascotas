package Animal;

import Animal.Excepciones.AnimalNoCreadoException;
import Animal.Excepciones.YaSubcriptoException;
import Refugio.Consulta;
import Refugio.Refugio;
import Refugio.RepositorioAnimalesEnBusqueda;
import Refugio.RepositorioAnimalesSinDuenio;

import java.util.ArrayList;

public abstract class Animal {
  private String nombre;
  private Duenio duenio;
  private EstadoAnimal estado;
  private ArrayList<Consulta> consultas;
  private Dieta tipoDieta;
  private Refugio refugioRelacionado;

  protected Animal(String nombre) {
    this.nombre = nombre;
    this.estado = null;
    this.consultas = new ArrayList<>();
    this.duenio = null;
    this.tipoDieta = null;
    this.refugioRelacionado = null;
  }

  public void subcribirAunRefugio(Refugio refugio){
    if(this.refugioRelacionado == null)
      this.refugioRelacionado = refugio;
    else
      throw new YaSubcriptoException("ya pertence al local con direccion " + this.refugioRelacionado.getDireccion()+ " que es un " +this.refugioRelacionado.getClass());
  }

  public String getNombre() {
    return nombre;
  }

  public Refugio getRefugioRelacionado() {
    return refugioRelacionado;
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

  public static Animal createAnimal(TipoAnimales tipoAnimal, String nombre){
    if(tipoAnimal==TipoAnimales.GATO){
      return new Gato(nombre);
    }
    if(tipoAnimal==TipoAnimales.PERRO){
      return new Perro(nombre);
    }
    if(tipoAnimal==TipoAnimales.EXOTICO){
      return new AnimalExotico(nombre);
    }
    throw new AnimalNoCreadoException("No se lleno con un tipo de animal correcto");
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
