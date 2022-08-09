package Refugio;

import Animal.Duenio;

import java.util.Set;

public abstract class Refugio {

  private Set<Rescatista> rescatistas;
  private Set<Veterinario> veterinarios;
  private Set<Duenio> duenios;
  private String direccion;

  public abstract int cantidadAnimales();
}
