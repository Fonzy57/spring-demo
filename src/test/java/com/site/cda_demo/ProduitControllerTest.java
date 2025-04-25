package com.site.cda_demo;


import com.site.cda_demo.controller.ProduitController;
import com.site.cda_demo.mock.MockProduitDao;
import com.site.cda_demo.mock.MockSecurityUtils;
import com.site.cda_demo.model.Produit;
import com.site.cda_demo.model.Utilisateur;
import com.site.cda_demo.security.AppUserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProduitControllerTest {

  ProduitController produitController;

  @BeforeEach
  void setUp() {
    produitController = new ProduitController(
        new MockProduitDao(), new MockSecurityUtils("ROLE_UTILISATEUR")
    );
  }


  @Test
  void callGetWithExistingProduct_shouldSendCode200ok() {
    ResponseEntity<Produit> reponse = produitController.get(1);
    Assertions.assertEquals(HttpStatus.OK, reponse.getStatusCode());
  }

  @Test
  void callGetWithNotExistingProduct_shouldSendCode404() {
    ResponseEntity<Produit> reponse = produitController.get(2);
    Assertions.assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
  }

  @Test
  void deleteExistingProductByOwner_shouldSendCode204NoContent() {
    Utilisateur fauxUtilisateur = new Utilisateur();
    fauxUtilisateur.setId(1);
    AppUserDetails userDetails = new AppUserDetails(fauxUtilisateur);


    ResponseEntity<Produit> reponse = produitController.delete(1, userDetails);
    Assertions.assertEquals(HttpStatus.NO_CONTENT, reponse.getStatusCode());
  }

  @Test
  void deleteExistingProductByNotOwner_shouldSendCode403Forbidden() {
    Utilisateur fauxUtilisateur = new Utilisateur();
    fauxUtilisateur.setId(2);
    AppUserDetails userDetails = new AppUserDetails(fauxUtilisateur);


    ResponseEntity<Produit> reponse = produitController.delete(1, userDetails);
    Assertions.assertEquals(HttpStatus.FORBIDDEN, reponse.getStatusCode());
  }

  @Test
  void deleteNotExistingProductByOwner_shouldSendCode404NotFound() {
    Utilisateur fauxUtilisateur = new Utilisateur();
    fauxUtilisateur.setId(1);
    AppUserDetails userDetails = new AppUserDetails(fauxUtilisateur);


    ResponseEntity<Produit> reponse = produitController.delete(2, userDetails);
    Assertions.assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
  }

  @Test
  void deleteExistingProductByNotOwnerButAdmin_shouldSendCode204NoContent() {
    produitController = new ProduitController(
        new MockProduitDao(), new MockSecurityUtils("ROLE_ADMINISTRATEUR")
    );

    Utilisateur fauxUtilisateur = new Utilisateur();
    fauxUtilisateur.setId(2);
    AppUserDetails userDetails = new AppUserDetails(fauxUtilisateur);


    ResponseEntity<Produit> reponse = produitController.delete(1, userDetails);
    Assertions.assertEquals(HttpStatus.NO_CONTENT, reponse.getStatusCode());
  }

}
