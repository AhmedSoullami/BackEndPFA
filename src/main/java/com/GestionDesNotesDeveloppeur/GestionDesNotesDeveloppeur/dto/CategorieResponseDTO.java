package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategorieResponseDTO {
    private Long id;
    private String nomCategorie;
    private Long idUser;
}
