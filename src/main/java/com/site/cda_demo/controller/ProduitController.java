package com.site.cda_demo.controller;

import com.site.cda_demo.dao.ProduitDao;
import com.site.cda_demo.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProduitController {

  @Autowired // Donne à ProduitController le ProduitDao
  protected ProduitDao produitDao;

  @GetMapping("/pepsi")
  public Produit get() {

    Produit pepsi = new Produit();
    pepsi.setId(1);
    pepsi.setNom("Pepsi");
    pepsi.setPrix(100);
    pepsi.setDescription("Meilleur soda au monde");

    return pepsi;
  }

  @GetMapping("/produits")
  public List<Produit> getAll() {

//    List<Produit> produits = new ArrayList<Produit>();
//
//    Produit pepsi = new Produit();
//    pepsi.setNom("Pepsi");
//    pepsi.setDescription("Meilleur soda au monde");
//
//    Produit coca = new Produit();
//    coca.setNom("Coca-Cola");
//    coca.setDescription("Comme Pepsi mais beaucoup moins bon...");
//
//    produits.add(pepsi);
//    produits.add(coca);
//
//    return produits;

    return produitDao.findAll();
  }

}
