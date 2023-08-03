package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MessageConnexion {
      @GetMapping("/message")
      public ResponseEntity<List<String>>message(){
      return ResponseEntity.ok(Arrays.asList("ahmed","soulllami"));
      }

}
