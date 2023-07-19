package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie createCategorie(Categorie categorie);
    Categorie getCategorie(Long id);
    List<Categorie> getAllCatrgorie();

}
