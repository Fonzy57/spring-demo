package com.site.cda_demo.controller;

import com.site.cda_demo.dao.EtatDao;
import com.site.cda_demo.model.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class EtatController {

  protected EtatDao etatDao;

  @Autowired // Donne à EtatController le EtatDao
  public EtatController(EtatDao etatDao) {
    this.etatDao = etatDao;
  }

  /**
   * Récupère tous les états
   *
   * @return liste des états
   */
  @GetMapping("/etats")
  public List<Etat> getAll() {
    return etatDao.findAll();
  }

  /**
   * Retourne une ResponseEntity, en gros un état selon l'id passé en paramètre
   *
   * @param id l'id de l'état à retourner
   * @return 404 si aucun état trouvé, ou statut 200 avec l'état
   */
  @GetMapping("/etat/{id}")
  public ResponseEntity<Etat> get(@PathVariable int id) {

    Optional<Etat> optionalEtat = etatDao.findById(id);

    if (optionalEtat.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalEtat.get(), HttpStatus.OK);
  }

  @PostMapping("/etat")
  public ResponseEntity<Etat> save(@RequestBody Etat etat) {
    etatDao.save(etat);

    return new ResponseEntity<>(etat, HttpStatus.CREATED);
  }

  @DeleteMapping("/etat/{id}")
  public ResponseEntity<Etat> delete(@PathVariable int id) {
    Optional<Etat> optionalEtat = etatDao.findById(id);

    if (optionalEtat.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    etatDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // PUT pour modifier tout l'objet
  @PutMapping("/etat/{id}")
  public ResponseEntity<Etat> update(@PathVariable int id, @RequestBody Etat etat) {

    Optional<Etat> optionalEtat = etatDao.findById(id);

    if (optionalEtat.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    etat.setId(id);
    etatDao.save(etat);

    return new ResponseEntity<>(etat, HttpStatus.NO_CONTENT);
  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
