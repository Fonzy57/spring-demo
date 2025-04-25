package com.site.cda_demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.site.cda_demo.dao.UtilisateurDao;
import com.site.cda_demo.model.Utilisateur;
import com.site.cda_demo.security.ISecurityUtils;
import com.site.cda_demo.security.IsAdministrateur;
import com.site.cda_demo.view.AffichageUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {

  protected UtilisateurDao utilisateurDao;
  protected ISecurityUtils securityUtils;

  @Autowired // Donne à UtilisateurController le UtilisateurDao // Injection de dépendance
  public UtilisateurController(UtilisateurDao utilisateurDao, ISecurityUtils securityUtils) {
    this.utilisateurDao = utilisateurDao;
    this.securityUtils = securityUtils;
  }

  /**
   * Recupère tous les utilisateurs
   *
   * @return liste des utilisateurs
   */
  @IsAdministrateur
  @JsonView(AffichageUtilisateur.class)
  @GetMapping("/utilisateurs")
  public List<Utilisateur> getAll() {
    return utilisateurDao.findAll();
  }

  /**
   * Retourne une ResponseEntity, en gros un utilisateur selon l'id passé en paramètre
   *
   * @param id l'id de l'utilisateur à retourner
   * @return 404 si aucun utilisateur trouvé, ou statut 200 avec le utilisateur
   */
  @GetMapping("/utilisateur/{id}")
  @IsAdministrateur
  @JsonView(AffichageUtilisateur.class)
  public ResponseEntity<Utilisateur> get(@PathVariable Long id) {

    Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

    if (optionalUtilisateur.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
  }

//  @IsAdministrateur
//  @DeleteMapping("/utilisateur/{id}")
//  public ResponseEntity<Utilisateur> delete(@PathVariable int id, @AuthenticationPrincipal AppUserDetails userDetails) {
//    Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);
//
//    if (optionalUtilisateur.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    // On récupère le role
//    String role = securityUtils.getRole(userDetails);
//
//    // Ici on autorise le role admin a supprimer un utilisateur qui n'est pas à lui
//    if (!role.equals("ROLE_ADMINISTRATEUR") &&
//        // Si l'id du créateur du utilisateur est different de l'id de la personne connectée
//        optionalUtilisateur.get().getCreateur().getId() != userDetails.getUtilisateur().getId()) {
//      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//    }
//
//    utilisateurDao.deleteById(id);
//
//    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//  }

  // PUT pour modifier tout l'objet
//  @PutMapping("/utilisateur/{id}")
//  @IsAdministrateur
//  public ResponseEntity<Utilisateur> update(
//      @PathVariable int id,
//      @RequestBody @Valid Utilisateur utilisateur,
//      @AuthenticationPrincipal AppUserDetails userDetails) {
//
//    Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);
//
//    if (optionalUtilisateur.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    utilisateur.setId(id);
//    utilisateur.setCreateur(userDetails.getUtilisateur());
//    utilisateurDao.save(utilisateur);
//
//    return new ResponseEntity<>(utilisateur, HttpStatus.OK);
//  }

  // PATCH pour modifier une partie de l'opbjet
  // @PatchMapping()


}
