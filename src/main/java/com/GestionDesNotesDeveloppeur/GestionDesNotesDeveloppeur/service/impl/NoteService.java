package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.impl;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Note;

import java.util.List;

public interface NoteService {
    Note createNote(Note note);
    Note updateNote(Note note);
    void deleteNoteById(Long id);
    Note getNote(Long id);
    List<Note> getAllNote();


}
