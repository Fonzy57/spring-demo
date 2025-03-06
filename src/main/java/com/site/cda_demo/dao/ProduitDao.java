package com.site.cda_demo.dao;

import com.site.cda_demo.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Crée une dépendance
public interface ProduitDao extends JpaRepository<Produit, Integer> { // Integer c'est le type de l'ID
}
