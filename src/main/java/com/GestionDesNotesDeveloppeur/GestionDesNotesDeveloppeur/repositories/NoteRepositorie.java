package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepositorie extends JpaRepository<Note,Long> {

    boolean existsByCle(String key);
    boolean existsByValeur(String value);



    List<Note> getAllNotesByCategorieId(Long idCategorie);

    List<Note> findByCategorie(Categorie categorie);

    boolean existsByCleAndValeur(String cleNote, String valeurNote);

    List<Note> findByCleContaining(String cleRecherche);
}
