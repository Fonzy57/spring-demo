package com.site.cda_demo.controller;

import com.site.cda_demo.dao.ProduitDao;
import com.site.cda_demo.model.Etat;
import com.site.cda_demo.model.Produit;
import com.site.cda_demo.security.AppUserDetails;
import com.site.cda_demo.security.ISecurityUtils;
import com.site.cda_demo.security.IsAdministrateur;
import com.site.cda_demo.security.IsUtilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ProduitController {

  protected ProduitDao produitDao;
  protected ISecurityUtils securityUtils;

  @Autowired // Donne à ProduitController le ProduitDao // Injection de dépendance
  public ProduitController(ProduitDao produitDao, ISecurityUtils securityUtils) {
    this.produitDao = produitDao;
    this.securityUtils = securityUtils;
  }

  /**
   * Recupère tous les produits
   *
   * @return liste de produits
   */
  @IsUtilisateur
  @GetMapping("/produits")
  public List<Produit> getAll() {
    return produitDao.findAll();
  }

  /**
   * Retourne une ResponseEntity, en gros un produit selon l'id passé en paramètre
   *
   * @param id l'id du produit à retourner
   * @return 404 si aucun produit trouvé, ou statut 200 avec le produit
   */
  @GetMapping("/produit/{id}")
  @IsAdministrateur
  public ResponseEntity<Produit> get(@PathVariable int id) {

    Optional<Produit> optionalProduit = produitDao.findById(id);

    if (optionalProduit.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalProduit.get(), HttpStatus.OK);
  }


  @PostMapping("/produit")
  @IsAdministrateur
  public ResponseEntity<Produit> save(
      @RequestBody @Valid Produit produit,
      @AuthenticationPrincipal AppUserDetails userDetails) {

    produit.setCreateur(userDetails.getUtilisateur());

    // Si le produit reçu n'a pas d'état alors, on indique qu'il est neuf par défaut
    if (produit.getEtat() == null) {
      Etat etatNeuf = new Etat();
      etatNeuf.setId(1);
      produit.setEtat(etatNeuf);
    }

    produitDao.save(produit);

    return new ResponseEntity<>(produit, HttpStatus.CREATED);
  }

  @IsAdministrateur
  @DeleteMapping("/produit/{id}")
  public ResponseEntity<Produit> delete(@PathVariable int id, @AuthenticationPrincipal AppUserDetails userDetails) {
    Optional<Produit> optionalProduit = produitDao.findById(id);

    if (optionalProduit.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // On récupère le role
    String role = securityUtils.getRole(userDetails);

    // Ici on autorise le role admin a supprimer un produit qui n'est pas à lui
    if (!role.equals("ROLE_ADMINISTRATEUR") &&
        // Si l'id du créateur du produit est different de l'id de la personne connectée
        optionalProduit.get().getCreateur().getId() != userDetails.getUtilisateur().getId()) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    produitDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // PUT pour modifier tout l'objet
  @PutMapping("/produit/{id}")
  @IsAdministrateur
  public ResponseEntity<Produit> update(
      @PathVariable int id,
      @RequestBody @Valid Produit produit,
      @AuthenticationPrincipal AppUserDetails userDetails) {

    Optional<Produit> optionalProduit = produitDao.findById(id);

    if (optionalProduit.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    produit.setId(id);
    produit.setCreateur(userDetails.getUtilisateur());
    produitDao.save(produit);

    return new ResponseEntity<>(produit, HttpStatus.OK);
  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
