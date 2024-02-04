package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.CategorieRequestDTO;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.CategorieResponseDTO;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Note;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.CategorieRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.NoteRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.UtilisateurRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.CategoryService;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.impl.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorie")
@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    private CategorieRepositorie categorieRepositorie;
    @Autowired
    private UtilisateurRepositorie utilisateurRepositorie;
    @Autowired
    private NoteRepositorie noteRepositorie;
    @Autowired
    private CategoryService categoryService;


    @Autowired
    public CategorieController(CategorieRepositorie categorieRepositorie, UtilisateurRepositorie utilisateurRepositorie) {
        this.categorieRepositorie = categorieRepositorie;
        this.utilisateurRepositorie = utilisateurRepositorie;

    }

    @PostMapping("/new/{idUser}")
    public Categorie createCategorie(@PathVariable("idUser") Long id, @RequestBody CategorieRequestDTO categorieRequest) {
        String nomCategorie = categorieRequest.getNomCategorie();
        if (categorieRepositorie.existsByNomCategorie(nomCategorie)) {
            return null;
        }
        Utilisateur utilisateur = utilisateurRepositorie.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Categorie nouvelleCategorie = new Categorie();
        nouvelleCategorie.setNomCategorie(nomCategorie);
        nouvelleCategorie.setUser(utilisateur);
        return categorieRepositorie.save(nouvelleCategorie);
    }

    @PutMapping("/update/{id}")

    public Categorie updateCategorie(@PathVariable("id") Long id, @RequestBody CategorieRequestDTO categorieRequest) {
        String nomCategorie = categorieRequest.getNomCategorie();

        Categorie categorie = categorieRepositorie.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Catégorie non trouvée"));
        if (!categorie.getNomCategorie().equals(nomCategorie) && categorieRepositorie.existsByNomCategorie(nomCategorie)) {
            throw new IllegalArgumentException("La catégorie existe déjà");
        }
        categorie.setNomCategorie(nomCategorie);
        return categorieRepositorie.save(categorie);
    }


    @GetMapping("/categories/{id}")
    public ResponseEntity<?> listeCategorieUtilisateurConnecte(@PathVariable("id") Long idUser) {
        List<Categorie> categories = categorieRepositorie.getAllCategoriesByUserId(idUser);

        List<CategorieResponseDTO> responseDTOs = new ArrayList<>();

        for (Categorie categorie : categories) {
            CategorieResponseDTO responseDTO = new CategorieResponseDTO();
            responseDTO.setNomCategorie(categorie.getNomCategorie());
            responseDTO.setIdUser(categorie.getUser().getId());
            responseDTO.setId(categorie.getId());
            responseDTOs.add(responseDTO);
        }

        return ResponseEntity.ok(responseDTOs);
    }

    @DeleteMapping("/deleteCategorie/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable("id") Long id) {
        try {
            Categorie categorie = categorieRepositorie.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Catégorie non trouvée"));

            List<Note> notes = noteRepositorie.findByCategorie(categorie);

            for (Note note : notes) {
                noteRepositorie.delete(note);
            }

            categorieRepositorie.delete(categorie);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur de suppression");
        }
    }
    @GetMapping("/rechercher")
    public ResponseEntity<List<Categorie>> rechercherParNom(@RequestParam String nomCategorie) {
        List<Categorie> resultats = categoryService.rechercherParNom(nomCategorie);
        return ResponseEntity.ok(resultats);
    }




}
