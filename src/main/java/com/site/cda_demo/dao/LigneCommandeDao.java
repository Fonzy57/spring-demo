package com.site.cda_demo.dao;

import com.site.cda_demo.model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Crée une dépendance
public interface LigneCommandeDao extends JpaRepository<LigneCommande, Integer> { // Integer c'est le type de l'ID
}
