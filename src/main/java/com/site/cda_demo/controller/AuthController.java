package com.site.cda_demo.controller;

import com.site.cda_demo.dao.UtilisateurDao;
import com.site.cda_demo.model.Utilisateur;
import com.site.cda_demo.security.AppUserDetails;
import com.site.cda_demo.security.Role;
import com.site.cda_demo.security.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {

  protected UtilisateurDao utilisateurDao;
  protected PasswordEncoder passwordEncoder;
  protected AuthenticationProvider authenticationProvider;
  protected SecurityUtils securityUtils;

  @Autowired
  public AuthController(UtilisateurDao utilisateurDao, PasswordEncoder passwordEncoder, AuthenticationProvider authenticationProvider, SecurityUtils securityUtils) {
    this.utilisateurDao = utilisateurDao;
    this.passwordEncoder = passwordEncoder;
    this.authenticationProvider = authenticationProvider;
    this.securityUtils = securityUtils;
  }

  @PostMapping("/inscription")
  public ResponseEntity<Utilisateur> inscription(@RequestBody @Valid Utilisateur utilisateur) {

    utilisateur.setRole(Role.UTILISATEUR);
    utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

    // On enregistre l'utilisateur en BDD
    utilisateurDao.save(utilisateur);

    // On masque le mot de passe lors de l'affichage
    utilisateur.setPassword(null);

    return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
  }

  @PostMapping("/connexion")
  public ResponseEntity<String> connexion(@RequestBody @Valid Utilisateur utilisateur) {
    try {
      AppUserDetails userDetails = (AppUserDetails) authenticationProvider.authenticate(
              new UsernamePasswordAuthenticationToken(
                  utilisateur.getEmail(),
                  utilisateur.getPassword()
              ))
          .getPrincipal();

      return new ResponseEntity<>(securityUtils.generateToken(userDetails), HttpStatus.OK);

    } catch (AuthenticationException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}





