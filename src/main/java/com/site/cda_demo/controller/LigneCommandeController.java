package com.site.cda_demo.controller;

import com.site.cda_demo.dao.LigneCommandeDao;
import com.site.cda_demo.model.LigneCommande;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class LigneCommandeController {

  protected LigneCommandeDao ligneCommandeDao;

  @Autowired // Donne à LigneCommandeController le LigneCommandeDao // Injection de dépendance
  public LigneCommandeController(LigneCommandeDao ligneCommandeDao) {
    this.ligneCommandeDao = ligneCommandeDao;
  }

  /**
   * Recupère tous les ligneCommandes
   *
   * @return liste de ligneCommandes
   */
  @GetMapping("/ligne-commandes")
  public List<LigneCommande> getAll() {
    return ligneCommandeDao.findAll();
  }

  /**
   * Retourne une ResponseEntity, en gros un ligneCommande selon l'id passé en paramètre
   *
   * @param id l'id du ligneCommande à retourner
   * @return 404 si aucun ligneCommande trouvé, ou statut 200 avec le ligneCommande
   */
  @GetMapping("/ligne-commande/{id}")
  public ResponseEntity<LigneCommande> get(@PathVariable int id) {

    Optional<LigneCommande> optionalLigneCommande = ligneCommandeDao.findById(id);

    if (optionalLigneCommande.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalLigneCommande.get(), HttpStatus.OK);
  }


  @PostMapping("/ligne-commande")
  public ResponseEntity<LigneCommande> save(@RequestBody @Valid LigneCommande ligneCommande) {
    ligneCommandeDao.save(ligneCommande);

    return new ResponseEntity<>(ligneCommande, HttpStatus.CREATED);
  }

  @DeleteMapping("/ligne-commande/{id}")
  public ResponseEntity<LigneCommande> delete(@PathVariable int id) {
    Optional<LigneCommande> optionalLigneCommande = ligneCommandeDao.findById(id);

    if (optionalLigneCommande.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ligneCommandeDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // PUT pour modifier tout l'objet
  @PutMapping("/ligne-commande/{id}")
  public ResponseEntity<LigneCommande> update(@PathVariable int id, @RequestBody @Valid LigneCommande ligneCommande) {

    Optional<LigneCommande> optionalLigneCommande = ligneCommandeDao.findById(id);

    if (optionalLigneCommande.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ligneCommande.setId(id);
    ligneCommandeDao.save(ligneCommande);

    return new ResponseEntity<>(ligneCommande, HttpStatus.NO_CONTENT);
  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
