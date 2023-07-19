package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Note;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.NoteRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotService implements NoteService {

    @Autowired
    NoteRepositorie noteRepositorie;
    @Override
    public Note createNote(Note note) {
        return noteRepositorie.save(note) ;
    }

    @Override
    public Note updateNote(Note note) {
        return noteRepositorie.save(note);
    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepositorie.deleteById(id);

    }

    @Override
    public Note getNote(Long id) {
        return noteRepositorie.findById(id).get();
    }

    @Override
    public List<Note> getAllNote() {
        return noteRepositorie.findAll();
    }
}
