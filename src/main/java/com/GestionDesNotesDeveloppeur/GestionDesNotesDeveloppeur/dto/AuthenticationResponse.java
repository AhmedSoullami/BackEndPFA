package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String email;
    private String accessToken;

}
