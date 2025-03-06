package com.site.cda_demo.dao;

import com.site.cda_demo.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Crée une dépendance
public interface EtatDao extends JpaRepository<Etat, Integer> {
}
