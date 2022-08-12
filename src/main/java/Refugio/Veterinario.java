package Refugio;

import Animal.Animal;
import Animal.Dieta;

import java.util.ArrayList;

public class Veterinario {

  private String nombreCompleto;
  private String cuit;
  private ArrayList<Consulta> consultas;

  public Veterinario(String nombreCompleto, String cuit) {
    this.nombreCompleto = nombreCompleto;
    this.cuit = cuit;
    this.consultas = new ArrayList<>();
  }

  public Consulta darConsulta(Animal animal, Dieta dieta, String observacionConsulta){
    Consulta consulta = new Consulta(this, animal, dieta, observacionConsulta);
    this.consultas.add(consulta);
    return consulta;
  }

  public void setConsultas(ArrayList<Consulta> consultas) {
    this.consultas = consultas;
  }

  public void agregarConsulta(Consulta consulta){
    this.consultas.add(consulta);
  }
}
