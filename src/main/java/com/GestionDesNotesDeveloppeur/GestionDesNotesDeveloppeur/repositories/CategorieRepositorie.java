package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepositorie extends JpaRepository<Categorie,Long> {

    List<Categorie> getAllCategoriesByUserId(Long userId);

    boolean existsByNomCategorie(String nomCategorie);

    Categorie getCategorieById(Long idCategorie);

    List<Categorie> findByNomCategorieContaining(String nomCategorie);
}
