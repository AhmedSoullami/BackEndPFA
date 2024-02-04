package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.UtilisateurRepositorie;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.impl.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UtilisateurService {
    @Autowired
    UtilisateurRepositorie utilisateurRepositorie;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur user) {

        return utilisateurRepositorie.save(user);
    }
    public Optional<Utilisateur> findUtilisateurByEmail(String email){
        return utilisateurRepositorie.findByEmail(email);
    }
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        Optional<Utilisateur> userOptional = utilisateurRepositorie.findById(id);

        if (userOptional.isPresent()) {
            Utilisateur user = userOptional.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                String hashedPassword = passwordEncoder.encode(newPassword);
                user.setMotDePasse(hashedPassword);
                saveUtilisateur(user);
                return true;
            }
        }

        return false;
    }


}
