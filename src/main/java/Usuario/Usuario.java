package Usuario;

import Usuario.Excepciones.ContraseniaEsInvalidaException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Usuario {

  private String mail;
  private String username;
  private final String salt = "+@351";
  private Boolean validado;
  private String contraHasheada;
  private ArrayList<CriterioValidacion> validadoresContrasenia;


  //////////////////////////////////  CONSTRUCTORES
  public Usuario(String username,String email,String contra,boolean validado) {
    if(!isContraseniaValida(contra)){
      throw new ContraseniaEsInvalidaException("no pasa por alguna de las validaciones de seguridad");
    }
    this.validado = validado;
    this.contraHasheada = generateHash(contra);
    this.username = username;
    this.mail = email;

    RepositorioUsuarios.getInstance().agregarUsuario(this);
  }

  //////////////////////////////////  GETTERS
  public boolean isValido(){
    return this.validado;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.mail;
  }

  //////////////////////////////////  SETTERS
  public void setValidado(Boolean validado) {
    this.validado = validado;
  }

  public boolean isColision(String contraAProbar){
    if(this.contraHasheada == null){
      //todo buscar contrasenia em DB si el usuario no tiene la contra

    }
    String contraHasheada = generateHash(contraAProbar);
    return this.contraHasheada.equals(contraHasheada);
  }


  private boolean isContraseniaValida(String contra){

    this.validadoresContrasenia = new ArrayList<>();

    this.validadoresContrasenia.add(new CriterioLongitud(8,80));
    this.validadoresContrasenia.add(new CriterioLetras());
    this.validadoresContrasenia.add(new CriterioNumeros());
    this.validadoresContrasenia.add(new CriterioSimbolosExtraños());

    for (CriterioValidacion criterioValidacion : this.validadoresContrasenia) {
      if (!criterioValidacion.validarContrasenia(contra))
        return false;
    }
    return true;
  }


  private String generateHash(String contrasenia) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    }catch (NoSuchAlgorithmException e) {
      throw new ContraseniaEsInvalidaException("La contrasenia no pudo ser hasheada");
    }
    digest.reset();
    digest.update(this.salt.getBytes(StandardCharsets.UTF_8));
    byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();

    for (byte b : hash) {
      sb.append(String.format("%02x", b));
    }

    return sb.toString();
  }
}
