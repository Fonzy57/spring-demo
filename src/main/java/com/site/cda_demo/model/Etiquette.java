package com.site.cda_demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.site.cda_demo.view.AffichageCommande;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Etiquette {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(nullable = false)
  @JsonView(AffichageCommande.class)
  protected String nom;
}
