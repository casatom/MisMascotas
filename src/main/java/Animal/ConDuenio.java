package Animal;

import Refugio.RepositorioAnimalesEnBusqueda;
import Refugio.RepositorioAnimalesSinDuenio;

public class ConDuenio extends EstadoAnimal {
  public ConDuenio(Animal animal) {
    super(animal);
  }

  @Override
  public boolean validarCambio() {
    //TODO Testear estas validaciones
    if(this.getAnimal().getEstado() == null){
      return true;
    }
    if(this.getAnimal().getEstado().getClass() == EnBusqueda.class){
      //Si se pasa de en busqueda se fija que no este todavia publicado
      if(RepositorioAnimalesEnBusqueda.getInstance().isEnBusqueda(this.getAnimal())){
        RepositorioAnimalesEnBusqueda.getInstance().animalEncontrado(this.getAnimal());
      }
    }
    if(this.getAnimal().getEstado().getClass() == SinDuenio.class){
      //Si se pasa de sin duenio se fija que no este todavia publicado
      if(RepositorioAnimalesSinDuenio.getInstance().isPublicado(this.getAnimal())){
        RepositorioAnimalesSinDuenio.getInstance().eliminarAnimalPublicado(this.getAnimal());
      }
    }
    return true;
  }
}
