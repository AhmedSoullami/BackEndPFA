package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.CategorieResponseDTO;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.NoteRequestDTO;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.NoteResponseDTO;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Note;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.CategorieRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.NoteRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.NotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/note")
@RestController
@CrossOrigin(origins = "*")

public class NoteController {
    @Autowired
    private NoteRepositorie noteRepositorie;
    @Autowired
    private CategorieRepositorie categorieRepositorie;
    @Autowired
    private NotService notService;
    @PostMapping("/ajouterNote/{id}")
    public Note createNote(@PathVariable("id") Long idCategorie, @RequestBody NoteRequestDTO noteRequestDTO){
        String cleNote=noteRequestDTO.getCle();
        String valeurNote=noteRequestDTO.getValeur();
        Long idNote=noteRequestDTO.getId();
        boolean noteExists = noteRepositorie.existsByCleAndValeur(cleNote, valeurNote);
        if (noteExists) {
            return null;
        }
        Categorie categorie=categorieRepositorie.findById(idCategorie).
                orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Note newNote=new Note();
        newNote.setCle(cleNote);
        newNote.setValeur(valeurNote);
        newNote.setCategorie(categorie);
        newNote.setId(idNote);
        return noteRepositorie.save(newNote);
    }
    @GetMapping("/notes/{id}")
    public ResponseEntity<?> listeNotes(@PathVariable("id")  Long idCategorie) {
        List<Note> notes = noteRepositorie.getAllNotesByCategorieId(idCategorie);

        List<NoteResponseDTO> noteResponseDTOs= new ArrayList<>();

        for (Note note : notes) {
            NoteResponseDTO noteResponseDTO = new NoteResponseDTO();
            noteResponseDTO.setId(note.getId());
            noteResponseDTO.setCle(note.getCle());
            noteResponseDTO.setValeur(note.getValeur());
            noteResponseDTO.setIdCategorie(note.getCategorie().getId());
            noteResponseDTOs.add(noteResponseDTO);
        }

        return ResponseEntity.ok(noteResponseDTOs);
    }
    @DeleteMapping("/supprimerNote/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable("id") Long id) {
        try {
            Note note = noteRepositorie.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Note non trouvée"));

            noteRepositorie.delete(note);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("erreur de suppression");
        }
    }
    @PutMapping("/modifierNote/{id}")
    public Note updateNote(@PathVariable("id") Long id, @RequestBody NoteRequestDTO noteRequestDTO) {
        String cleNote = noteRequestDTO.getCle();
        String valeurNote = noteRequestDTO.getValeur();
        Note note = noteRepositorie.findById(id).orElseThrow(() -> new IllegalArgumentException("Note non trouvée"));
        if (!note.getCle().equals(cleNote) && noteRepositorie.existsByCle(cleNote)) {
            throw new IllegalArgumentException("La clé de la note existe déjà");
        }
        if (!note.getValeur().equals(valeurNote) && noteRepositorie.existsByValeur(valeurNote)) {
            throw new IllegalArgumentException("La valeur de la note existe déjà");
        }
        note.setCle(cleNote);
        note.setValeur(valeurNote);
        return noteRepositorie.save(note);
    }
    @GetMapping("/getNote/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        Optional<Note> noteOptional = noteRepositorie.findById(id);

        if (noteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(noteOptional.get());
    }
    @GetMapping("/rechercher")
    public ResponseEntity<List<Note>> rechercherParCle(@RequestParam String cleRecherche) {
        List<Note> resultats = notService.rechercherParCle(cleRecherche);
        return ResponseEntity.ok(resultats);
    }

}
