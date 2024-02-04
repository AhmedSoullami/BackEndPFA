package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NoteRequestDTO {
    private Long id;
    private String cle;
    private String valeur;
}
