package com.site.cda_demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.site.cda_demo.view.AffichageCommande;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Produit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(nullable = false)
  @NotBlank
  @JsonView(AffichageCommande.class)
  protected String nom;

  @Column(length = 15, nullable = false, unique = true)
  @Length(max = 15, min = 3, message = "Longueur entre 3 et 10")
  @NotBlank
  @JsonView(AffichageCommande.class)
  protected String code;

  @Column(columnDefinition = "TEXT")
  @JsonView(AffichageCommande.class)
  protected String description;

  @DecimalMin(value = "0.1")
  protected float prix;

  @ManyToOne
  @JoinColumn(nullable = false)
  @JsonView(AffichageCommande.class)
  protected Etat etat;

  @ManyToMany
  @JoinTable(
      name = "etiquette_produit", // Permet de changer le nom de la table de jointure
      // joinColumns = @JoinColumn("produit_id"), // Ici en commentaire, car on est dans l'entité Produit
      // et la colonne en BDD s'apelle déjà 'produit_id'
      inverseJoinColumns = @JoinColumn(name = "etiquette_id")
  )
  @JsonView(AffichageCommande.class)
  protected List<Etiquette> etiquettes = new ArrayList<>();

  // TODO ICI POUR RESERVER QUELQUE CHOSE SELON UN ROLE BIEN PRECIS
  @ManyToOne
  @JoinColumn(nullable = false)
  Utilisateur createur;

  @Override
  public String toString() {
    return "Produit{" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", code='" + code + '\'' +
        ", description='" + description + '\'' +
        ", prix=" + prix +
        ", etat=" + etat +
        ", etiquettes=" + etiquettes +
        ", createur=" + createur +
        '}';
  }
}
