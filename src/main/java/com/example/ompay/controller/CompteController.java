package com.example.ompay.controller;

import jakarta.validation.Valid;

import com.example.ompay.dto.request.CompteRequestDTO;
import com.example.ompay.dto.response.CompteResponseDTO;
import com.example.ompay.entity.Compte;
import com.example.ompay.mapper.CompteMapper;
import com.example.ompay.service.CompteService;
import com.example.ompay.utils.ResponseHandler;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
@RequestMapping("api/comptes")
public class CompteController 
{
    private final CompteService compteService;
    private final CompteMapper compteMapper;

    public CompteController(CompteService compteService, CompteMapper compteMapper)
    {
        this.compteService = compteService;
        this.compteMapper = compteMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllComptes()
    {
        List<Compte> comptes = compteService.getAllComptes();
        
        List<CompteResponseDTO> dtos = comptes.stream()
        .map(compteMapper::toResponseDTO)
        .toList();
        return ResponseHandler.success("Liste des comptes récupérée avec succès", dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompteById(@PathVariable UUID id)
    {
        Optional<Compte> optionalCompte = compteService.getCompteById(id);
        System.out.println("compte : " + optionalCompte.get());

        if (optionalCompte.isEmpty()) {
            return ResponseHandler.error("Compte non trouvé", HttpStatus.NOT_FOUND);
        }
        CompteResponseDTO dto = compteMapper.toResponseDTO(optionalCompte.get());         
        return ResponseHandler.success("Compte récupéré avec succès", dto, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMonCompte(@AuthenticationPrincipal Compte compteConnecte) {
        if (compteConnecte == null) {
            return ResponseHandler.error("Utilisateur non authentifié", HttpStatus.UNAUTHORIZED);
        }

        // On récupère le compte complet depuis la base
        Compte compte = compteService.getCompteByTelephone(compteConnecte.getTelephone())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        CompteResponseDTO dto = compteMapper.toResponseDTO(compte);

        return ResponseHandler.success("Compte récupéré avec succès", dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCompte(@Valid @RequestBody CompteRequestDTO dto)
    {
        Compte compte = compteMapper.toEntity(dto);
        Compte saved = compteService.createCompte(compte);
        CompteResponseDTO response = compteMapper.toResponseDTO(saved);
        return ResponseHandler.success("Compte créé avec succès", response, HttpStatus.CREATED);
    }
}
