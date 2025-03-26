package com.site.cda_demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.site.cda_demo.dao.CommandeDao;
import com.site.cda_demo.model.Commande;
import com.site.cda_demo.view.AffichageCommande;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CommandeController {

  protected CommandeDao commandeDao;

  @Autowired // Donne à CommandeController le CommandeDao // Injection de dépendance
  public CommandeController(CommandeDao commandeDao) {
    this.commandeDao = commandeDao;
  }

  /**
   * Recupère tous les commandes
   *
   * @return liste de commandes
   */
  @GetMapping("/commandes")
  @JsonView(AffichageCommande.class)
  public List<Commande> getAll() {
    return commandeDao.findAll();
  }

  /**
   * Retourne une ResponseEntity, en gros une commande selon l'id passé en paramètre
   *
   * @param id l'id de la commande à retourner
   * @return 404 si aucune commande trouvée, ou statut 200 avec la commande
   */
  @GetMapping("/commande/{id}")
  @JsonView(AffichageCommande.class)
  public ResponseEntity<Commande> get(@PathVariable int id) {

    Optional<Commande> optionalCommande = commandeDao.findById(id);

    if (optionalCommande.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCommande.get(), HttpStatus.OK);
  }


  @PostMapping("/commande")
  public ResponseEntity<Commande> save(@RequestBody @Valid Commande commande) {
    commandeDao.save(commande);

    return new ResponseEntity<>(commande, HttpStatus.CREATED);
  }

  @DeleteMapping("/commande/{id}")
  public ResponseEntity<Commande> delete(@PathVariable int id) {
    Optional<Commande> optionalCommande = commandeDao.findById(id);

    if (optionalCommande.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    commandeDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // PUT pour modifier tout l'objet
  @PutMapping("/commande/{id}")
  public ResponseEntity<Commande> update(@PathVariable int id, @RequestBody @Valid Commande commande) {

    Optional<Commande> optionalCommande = commandeDao.findById(id);

    if (optionalCommande.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    commande.setId(id);
    commandeDao.save(commande);

    return new ResponseEntity<>(commande, HttpStatus.NO_CONTENT);
  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
