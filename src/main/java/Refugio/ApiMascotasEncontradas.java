package Refugio;


import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ApiMascotasEncontradas {

  private static ApiMascotasEncontradas instance = null;
  private int minLetras = 5;
  private int maxLetras = 8;
  private String keyAuth= "a052d490c8msh3cad08f2149048ap176aa8jsn90757b1ffd7b";
  private String host= "random-words5.p.rapidapi.com";
  private ArrayList<String> bufferLugares;
  private int asciiVal = 34;
  private String comilla ;
  private ArrayList<String> noCaracter;


  public ArrayList<Lugar> requestLugares(int cantidadLugares){
    HttpResponse<String> response = Unirest.get("https://random-words5.p.rapidapi.com/getMultipleRandom?count="+cantidadLugares+"&minLength="+this.minLetras+"&maxLength="+this.maxLetras)
        .header("X-RapidAPI-Key", this.keyAuth)
        .header("X-RapidAPI-Host", this.host)
        .asString();


    this.bufferLugares.addAll(Arrays.asList(response.getBody().split(this.comilla)));
    this.bufferLugares.removeAll(noCaracter);

    ArrayList<Lugar> lugaresNuevos = new ArrayList<>();

    for (String direccion: this.bufferLugares) {
      lugaresNuevos.add(new Lugar(direccion));
    }

    this.bufferLugares.clear();
    return lugaresNuevos;
  }

  public void setBufferLugares(ArrayList<String> bufferLugares) {
    this.bufferLugares = bufferLugares;
  }

  private ApiMascotasEncontradas(){
    this.bufferLugares = new ArrayList<>();
    this.comilla = Character.toString((char) asciiVal);
    this.noCaracter = new ArrayList<>();
    this.noCaracter.add("[");
    this.noCaracter.add(",");
    this.noCaracter.add("]");
  }

  public static ApiMascotasEncontradas getInstance(){
    if(instance == null){
      instance = new ApiMascotasEncontradas();
    }
    return instance;
  }

  public ArrayList<String> getBufferLugares() {
    return bufferLugares;
  }

  public static void main(String [] args) throws IOException {
      ApiMascotasEncontradas.getInstance().requestLugares(5);
  }
}
