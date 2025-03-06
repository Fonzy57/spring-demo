package com.site.cda_demo.dao;

import com.site.cda_demo.model.Etiquette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Crée une dépendance
public interface EtiquetteDao extends JpaRepository<Etiquette, Integer> {
}
