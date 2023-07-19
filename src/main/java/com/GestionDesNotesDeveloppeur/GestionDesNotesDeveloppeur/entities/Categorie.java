package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCategorie;
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur user;

}
