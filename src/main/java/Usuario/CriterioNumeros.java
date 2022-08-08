package Usuario;

public class CriterioNumeros implements CriterioValidacion{
  @Override
  public boolean validarContrasenia(String contra) {
    char ch;
    for(int i = 0; i < contra.length(); i++) {
      ch = contra.charAt(i);
      if (Character.isDigit(ch)) {
        return true;
      }
    }
    return false;
  }
}
