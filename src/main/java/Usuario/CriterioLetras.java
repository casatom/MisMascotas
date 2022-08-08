package Usuario;

public class CriterioLetras implements CriterioValidacion {
  @Override
  public boolean validarContrasenia(String contra) {
    char ch;
    boolean numberFlag;
    boolean capitalFlag = false;
    boolean lowerCaseFlag = false;
    for(int i = 0; i < contra.length(); i++) {
      ch = contra.charAt(i);
      if (Character.isUpperCase(ch)) {
        capitalFlag = true;
      } else if (Character.isLowerCase(ch)) {
        lowerCaseFlag = true;
      }
    }
    if(capitalFlag && lowerCaseFlag)
      return true;
    else
      return false;
  }
}
