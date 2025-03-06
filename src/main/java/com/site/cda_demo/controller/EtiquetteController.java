package com.site.cda_demo.controller;

import com.site.cda_demo.dao.EtiquetteDao;
import com.site.cda_demo.model.Etiquette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class EtiquetteController {

  protected EtiquetteDao etiquetteDao;

  @Autowired // Donne à EtiquetteController le EtiquetteDao
  public EtiquetteController(EtiquetteDao etiquetteDao) {
    this.etiquetteDao = etiquetteDao;
  }

  /**
   * Récupère tous les états
   *
   * @return liste des états
   */
  @GetMapping("/etiquettes")
  public List<Etiquette> getAll() {
    return etiquetteDao.findAll();
  }

  /**
   * Retourne une ResponseEntity, en gros un état selon l'id passé en paramètre
   *
   * @param id l'id de l'état à retourner
   * @return 404 si aucun état trouvé, ou statut 200 avec l'état
   */
  @GetMapping("/etiquette/{id}")
  public ResponseEntity<Etiquette> get(@PathVariable int id) {

    Optional<Etiquette> optionalEtiquette = etiquetteDao.findById(id);

    if (optionalEtiquette.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalEtiquette.get(), HttpStatus.OK);
  }

  @PostMapping("/etiquette")
  public ResponseEntity<Etiquette> save(@RequestBody Etiquette etiquette) {
    etiquetteDao.save(etiquette);

    return new ResponseEntity<>(etiquette, HttpStatus.CREATED);
  }

  @DeleteMapping("/etiquette/{id}")
  public ResponseEntity<Etiquette> delete(@PathVariable int id) {
    Optional<Etiquette> optionalEtiquette = etiquetteDao.findById(id);

    if (optionalEtiquette.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    etiquetteDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // PUT pour modifier tout l'objet
  @PutMapping("/etiquette/{id}")
  public ResponseEntity<Etiquette> update(@PathVariable int id, @RequestBody Etiquette etiquette) {

    Optional<Etiquette> optionalEtiquette = etiquetteDao.findById(id);

    if (optionalEtiquette.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    etiquette.setId(id);
    etiquetteDao.save(etiquette);

    return new ResponseEntity<>(etiquette, HttpStatus.NO_CONTENT);
  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
