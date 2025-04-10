package com.site.cda_demo.model;

import com.site.cda_demo.security.Role;
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
  protected Integer id;

  @NotBlank
  @Email
  @Column(unique = true, nullable = false)
  protected String email;

  @NotBlank
  @Column(nullable = false)
  protected String password;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('UTILISATEUR', 'REDACTEUR', 'ADMINISTRATEUR')")
  protected Role role;
}