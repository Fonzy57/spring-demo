package com.site.cda_demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.site.cda_demo.security.Role;
import com.site.cda_demo.view.AffichageUtilisateur;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Utilisateur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(AffichageUtilisateur.class)
  protected Integer id;

  @NotBlank
  @Email
  @Column(unique = true, nullable = false)
  @JsonView(AffichageUtilisateur.class)
  protected String email;

  @NotBlank
  @Column(nullable = false)
  protected String password;

  @Enumerated(EnumType.STRING)
  @JsonView(AffichageUtilisateur.class) // Supprimer après les tests
  @Column(columnDefinition = "ENUM('UTILISATEUR', 'REDACTEUR', 'ADMINISTRATEUR')")
  protected Role role;
}