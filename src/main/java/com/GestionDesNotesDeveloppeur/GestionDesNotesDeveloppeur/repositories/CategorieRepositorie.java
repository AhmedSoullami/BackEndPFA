package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepositorie extends JpaRepository<Categorie,Long> {

}
