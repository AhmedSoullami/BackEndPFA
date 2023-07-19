package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepositorie extends JpaRepository<Note,Long> {
}
