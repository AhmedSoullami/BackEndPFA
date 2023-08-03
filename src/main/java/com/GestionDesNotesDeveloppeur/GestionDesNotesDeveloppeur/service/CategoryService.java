package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Categorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.CategorieRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.impl.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryService implements CategorieService {
    @Autowired
    CategorieRepositorie categorieRepositorie;
    @Override
    public Categorie createCategorie(Categorie categorie) {
        return categorieRepositorie.save(categorie);
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepositorie.findById(id).get();
    }

    @Override
    public List<Categorie> getAllCatrgorie() {
        return categorieRepositorie.findAll();
    }
}
