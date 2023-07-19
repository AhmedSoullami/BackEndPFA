package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("api/auth")
public class AuthentificationController {
    private UtilisateurService utilisateurService;
    private AuthenticationManager authenticationManager;




}
