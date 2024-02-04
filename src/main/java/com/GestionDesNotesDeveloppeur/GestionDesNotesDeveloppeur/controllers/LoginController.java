package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.configuration.JwtTokenUtil;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.AuthenticationRequest;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.AuthenticationResponse;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.ChangeMotDePasse;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.UserService;
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
@RequestMapping({"/auth"})
@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;
    @PostMapping({"/login"})
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

    @GetMapping("/user-details")
    public ResponseEntity<Utilisateur> getUserDetails(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return ResponseEntity.ok(utilisateur);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangeMotDePasse request, Authentication authentication) {
        try {
            if (authentication != null && authentication.isAuthenticated()) {
                Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
                boolean passwordChanged = userService.changePassword(utilisateur.getId(), request.getOldPassword(), request.getNewPassword());

                if (passwordChanged) {
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec du changement de mot de passe");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non authentifié");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite");
        }
    }

}










