package com.site.cda_demo.controller;

import com.site.cda_demo.dao.ProduitDao;
import com.site.cda_demo.model.Etat;
import com.site.cda_demo.model.Produit;
import com.site.cda_demo.security.IsAdministrateur;
import com.site.cda_demo.security.IsRedacteur;
import com.site.cda_demo.security.IsUtilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ProduitController {

  protected ProduitDao produitDao;

  @Autowired // Donne à ProduitController le ProduitDao // Injection de dépendance
  public ProduitController(ProduitDao produitDao) {
    this.produitDao = produitDao;
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
  public ResponseEntity<Produit> save(@RequestBody @Valid Produit produit) {

    // Si le produit reçu n'a pas d'état alors, on indique qu'il est neuf par défaut
    if (produit.getEtat() == null) {
      Etat etatNeuf = new Etat();
      etatNeuf.setId(1);
      produit.setEtat(etatNeuf);
    }

    produitDao.save(produit);

    return new ResponseEntity<>(produit, HttpStatus.CREATED);
  }

  @IsRedacteur
  @DeleteMapping("/produit/{id}")
  public ResponseEntity<Produit> delete(@PathVariable int id) {
    Optional<Produit> optionalProduit = produitDao.findById(id);

    if (optionalProduit.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    produitDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // PUT pour modifier tout l'objet
  @PutMapping("/produit/{id}")
  public ResponseEntity<Produit> update(@PathVariable int id, @RequestBody @Valid Produit produit) {

    Optional<Produit> optionalProduit = produitDao.findById(id);

    if (optionalProduit.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    produit.setId(id);
    produitDao.save(produit);

    return new ResponseEntity<>(produit, HttpStatus.NO_CONTENT);
  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
