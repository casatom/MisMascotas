package Refugio;

import Animal.Animal;
import Animal.Dieta;

import java.time.LocalDate;
import java.util.Date;

public class Consulta {
  private LocalDate fechaConsulta;
  private Veterinario veterinario;
  private Animal animal;
  private Dieta dietaRecomendada;
  private String observacion;

  public Consulta(Veterinario veterinario, Animal animal, Dieta dietaRecomendada, String observacion) {
    this.fechaConsulta = LocalDate.now();
    this.veterinario = veterinario;
    this.animal = animal;
    this.dietaRecomendada = dietaRecomendada;
    this.observacion = observacion;

    guardarConsulta();
  }

  public void setFechaConsulta(LocalDate fechaConsulta) {
    this.fechaConsulta = fechaConsulta;
  }

  public void guardarConsulta(){
    //TODO guardar en la DB
  }
}
