package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.configuration.JwtTokenUtil;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.AuthenticationRequest;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.AuthenticationResponse;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping({"/auth/login"})
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
            String accesToken = this.jwtTokenUtil.generateAccessToken(utilisateur);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(utilisateur.getEmail(), accesToken);
            return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}





