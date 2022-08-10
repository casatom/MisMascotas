package Animal;

public class EnBusqueda extends EstadoAnimal {
  public EnBusqueda(Animal animal) {
    super(animal);
    if(validarCambio()){
      animal.publicarExtravio();
    }
  }

  @Override
  public boolean validarCambio() {
    //TODO VALIDAR LOS CAMBIOS
    if(this.getAnimal().getEstado().getClass() == ConDuenio.class){
      return true;
    }
    if(this.getAnimal().getEstado().getClass() == this.getClass()){
      return true;
    }
    return false;
  }
}
