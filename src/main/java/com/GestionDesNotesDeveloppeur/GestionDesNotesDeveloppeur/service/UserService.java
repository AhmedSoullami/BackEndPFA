package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.UtilisateurRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.impl.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService implements UtilisateurService {
    @Autowired
    UtilisateurRepositorie utilisateurRepositorie;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur user) {

        return utilisateurRepositorie.save(user);
    }
    public Optional<Utilisateur> findUtilisateurByEmail(String email){
        return utilisateurRepositorie.findByEmail(email);
    }
}
