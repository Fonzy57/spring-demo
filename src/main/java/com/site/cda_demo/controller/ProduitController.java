package com.site.cda_demo.controller;

import com.site.cda_demo.dao.ProduitDao;
import com.site.cda_demo.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProduitController {
  
  protected ProduitDao produitDao;

  @Autowired // Donne à ProduitController le ProduitDao
  public ProduitController(ProduitDao produitDao) {
    this.produitDao = produitDao;
  }

  @GetMapping("/produit/{id}")
  public ResponseEntity<Produit> get(@PathVariable int id) {

    Optional<Produit> optionalProduit = produitDao.findById(id);

    if (optionalProduit.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalProduit.get(), HttpStatus.OK);
  }

  @GetMapping("/produits")
  public List<Produit> getAll() {
    return produitDao.findAll();
  }
}
