package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepositorie extends JpaRepository<Utilisateur,Long> {

}
