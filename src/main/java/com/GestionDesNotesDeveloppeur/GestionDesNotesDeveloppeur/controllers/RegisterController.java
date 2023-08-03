package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.AuthenticationRequest;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.dto.AuthenticationResponse;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.entities.Utilisateur;
import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.UtilisateurRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/user"})
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired(required=true)
    private UtilisateurRepositorie utilisateurRepositorie;
    @Autowired(required=true)
    private AuthenticationResponse  authenticationResponse;

    @PostMapping({"/register"})
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request){
        if (utilisateurRepositorie.existsByNomUtilisateur(request.getUsername())) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        if (utilisateurRepositorie.existsByEmail(request.getEmail())) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());
        Utilisateur newUser = new Utilisateur(request.getUsername(),request.getEmail(), password);
        Utilisateur savedUser = (Utilisateur)this.utilisateurRepositorie.save(newUser);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(newUser.getNomUtilisateur(), newUser.getEmail());
        return ResponseEntity.ok().build();


    }
    @GetMapping("/list")
    public ResponseEntity<?> getUsers(){
        List<Utilisateur> users = utilisateurRepositorie.findAll();
        return ResponseEntity.ok(users);
    }

}
