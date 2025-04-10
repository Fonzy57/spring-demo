package com.site.cda_demo.security;

import com.site.cda_demo.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Agit comme un wrapper
public class AppUserDetails implements UserDetails {

  protected Utilisateur utilisateur;

  public AppUserDetails(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }

  // ICI QU'IL POURRAIT Y AVOIR DES DIFFERENCES
  // dépend des projets
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()));

    // Si plusieurs rôles dans une tâble à part :
    // return List.of(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().getNom());
  }

  @Override
  public String getPassword() {
    return utilisateur.getPassword();
  }

  @Override
  public String getUsername() {
    return utilisateur.getEmail();
  }
}
