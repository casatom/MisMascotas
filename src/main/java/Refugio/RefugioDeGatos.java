package Refugio;

import Animal.Gato;

import java.util.Set;

public class RefugioDeGatos extends Refugio {

  private Set<Gato> gatos;

  private RefugioDeGatos(String direccion) {
    super(direccion);
  }

  public static Refugio createRefugio(String direccion) {
    return new RefugioDeGatos(direccion);
  }


  public void agregarAnimal(Gato gato){
    gato.subcribirAunRefugio(this);
    this.gatos.add(gato);
  }


  @Override
  public int cantidadAnimales() {
    return this.gatos.size();
  }
}
