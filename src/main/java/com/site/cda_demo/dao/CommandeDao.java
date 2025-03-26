package com.site.cda_demo.dao;

import com.site.cda_demo.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Crée une dépendance
public interface CommandeDao extends JpaRepository<Commande, Integer> { // Integer c'est le type de l'ID
}
