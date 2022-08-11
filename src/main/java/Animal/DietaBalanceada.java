package Animal;

public class DietaBalanceada extends Dieta{
  public DietaBalanceada(String observacion) {
    super(observacion);
  }

  public DietaBalanceada() {
    super(null);
  }

  @Override
  public String getAlimentacion() {
    //TODO llenar alimnetacion Balanceada
    //Esta configuracion de la dieta podria ser guardada en la DB
    String alimentacionBalanceda = "La dieta Balanceada contiene\n";
    return alimentacionBalanceda + this.getObservacion();
  }
}
