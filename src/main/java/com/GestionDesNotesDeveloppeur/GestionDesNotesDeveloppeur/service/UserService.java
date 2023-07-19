package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.UtilisateurRepositorie;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements UtilisateurService{
    @Autowired
    UtilisateurRepositorie utilisateurRepositorie;
    @Override
    public Utilisateur saveUtilisateur(Utilisateur user) {

        return utilisateurRepositorie.save(user);
    }
}
