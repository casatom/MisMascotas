package Refugio;


import Animal.Excepciones.YaSubcriptoException;
import Animal.Gato;

import java.util.Set;

public class RefugioDeGatos extends Refugio {

  private Set<Gato> gatos;

  public RefugioDeGatos(String direccion) {
    super(direccion);
  }


  public void agregarGato(Gato gato){
    gato.subcribirAunRefugio(this);
    this.gatos.add(gato);
  }


  @Override
  public int cantidadAnimales() {
    return this.gatos.size();
  }
}
