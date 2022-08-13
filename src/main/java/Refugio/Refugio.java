package Refugio;

import Animal.Animal;
import Animal.Duenio;
import Refugio.Exception.CreacionRefugioException;

import java.util.HashSet;
import java.util.Set;

public abstract class Refugio {

  private Set<Rescatista> rescatistas;
  private Set<Veterinario> veterinarios;
  private Set<Duenio> duenios;
  private String direccion;

  protected Refugio(String direccion) {
    this.direccion = direccion;
    this.rescatistas = new HashSet<>();
    this.veterinarios = new HashSet<>();
    this.duenios = new HashSet<>();
  }

  public String getDireccion() {
    return direccion;
  }


  public static Refugio createRefugio(String direccion, TipoRefugios tipoRefugio){
    if(tipoRefugio == TipoRefugios.EXOTICOS){
      return new RefugioAnimalesExoticos(direccion);
    }
    if(tipoRefugio == TipoRefugios.GATOS){
      return new RefugioDeGatos(direccion);
    }
    if(tipoRefugio == TipoRefugios.PERROS){
      return new RefugioDePerros(direccion);
    }
    throw new CreacionRefugioException("no se lleno el tipo de refugio con un tipo correcto");
  }

  public void agregarRescatista(Rescatista rescatista){
    this.rescatistas.add(rescatista);
  }

  public void agregarVeterinario(Veterinario veterinario){
    this.veterinarios.add(veterinario);
  }

  public void agregarDuenio(Duenio duenio){
    duenio.agregarRefugio(this);
    this.duenios.add(duenio);
  }

  public abstract int cantidadAnimales();
}
