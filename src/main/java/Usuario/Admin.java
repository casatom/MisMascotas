package Usuario;

import Animal.Duenio;

public class Admin extends Usuario {

  public Admin(String username, String email, String contra, boolean validado) {
    super(username, email, contra, validado);

    setEsAdmin(true);
  }

  public Admin(Number id) {
    super(id);
  }

  public boolean validarUsuario(Usuario userDeAlta, boolean validacion){
    return RepositorioUsuarios.getInstance().validarUsuario(userDeAlta,validacion);
  }

  public boolean validarUsuario(String username, boolean validacion){
    return RepositorioUsuarios.getInstance().validarUsuario(username,validacion);
  }

  public void validarDuenio(Duenio duenio, boolean validado){
    duenio.setEsValidoParaAdoptar(validado);
  }
}
