package Usuario;

import java.util.ArrayList;

public class CriterioSimbolosExtraños implements CriterioValidacion{

  private String simbolos;

  public void agregarSimbolos(String simbolo){
    if(!this.simbolos.contains(simbolo)){
      this.simbolos = this.simbolos+simbolo;
    }
  }

  public CriterioSimbolosExtraños() {
    this.simbolos = "!·$%&/()=";
  }

  @Override
  public boolean validarContrasenia(String contra) {
    char ch;
    for(int i = 0; i < contra.length(); i++) {
      ch = contra.charAt(i);

      if (!Character.isDigit(ch) && !Character.isUpperCase(ch) && !Character.isLowerCase(ch)) {
        if( this.simbolos.indexOf(ch) < 0 ){
          return false;
        }
      }
    }
    return true;
  }


}
