package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepositorie extends JpaRepository<Utilisateur,Long> {
    boolean existsByNomUtilisateur(String nomUtilisateur);
    boolean existsByEmail(String email);

    Optional<Utilisateur> findByEmail(String email);

}


