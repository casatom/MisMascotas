package Refugio;


import Animal.Gato;

import java.util.Set;

public class RefugioDeGatos extends Refugio {

  private Set<Gato> gatos;


  public void agregarGato(Gato gato){
    this.gatos.add(gato);
  }


  @Override
  public int cantidadAnimales() {
    return this.gatos.size();
  }
}
