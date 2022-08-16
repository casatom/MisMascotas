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
  private Number id;

  public Consulta(Veterinario veterinario, Animal animal, Dieta dietaRecomendada, String observacion) {
    this.fechaConsulta = LocalDate.now();
    this.veterinario = veterinario;
    this.animal = animal;
    this.dietaRecomendada = dietaRecomendada;
    this.observacion = observacion;
  }

  public Consulta(Number id) {
    this.id = id;
  }

  public Number getId() {
    return id;
  }

  public LocalDate getFechaConsulta() {
    return fechaConsulta;
  }

  public Veterinario getVeterinario() {
    return veterinario;
  }

  public Animal getAnimal() {
    return animal;
  }

  public Dieta getDietaRecomendada() {
    return dietaRecomendada;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setId(Number id) {
    this.id = id;
  }

  public void setFechaConsulta(LocalDate fechaConsulta) {
    this.fechaConsulta = fechaConsulta;
  }

  public void guardarConsulta(){
    //TODO guardar en la DB
  }
}
