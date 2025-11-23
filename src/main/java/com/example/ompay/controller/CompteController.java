package com.example.ompay.controller;

import jakarta.validation.Valid;

import com.example.ompay.dto.request.CompteRequestDTO;
import com.example.ompay.dto.response.ApiResponse;
import com.example.ompay.dto.response.CompteResponseDTO;
import com.example.ompay.dto.response.TranfertResponseDTO;
import com.example.ompay.entity.Compte;
import com.example.ompay.mapper.CompteMapper;
import com.example.ompay.service.CompteService;
import com.example.ompay.service.TransactionService;
// import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/comptes")
public class CompteController
{
    private final CompteService compteService;
    private final CompteMapper compteMapper;
    private final TransactionService transactionService;

    public CompteController(CompteService compteService, CompteMapper compteMapper, TransactionService transactionService)
    {
        this.compteService = compteService;
        this.compteMapper = compteMapper;
        this.transactionService = transactionService;
    }

    // @GetMapping
    // public ResponseEntity<?> getAllComptes()
    // {
    //     List<Compte> comptes = compteService.getAllComptes();
        
    //     List<CompteResponseDTO> dtos = comptes.stream()
    //     .map(compteMapper::toResponseDTO)
    //     .toList();
    //     return ResponseHandler.success("Liste des comptes récupérée avec succès", dtos, HttpStatus.OK);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> getCompteById(@PathVariable UUID id)
    // {
    //     Optional<Compte> optionalCompte = compteService.getCompteById(id);
    //     System.out.println("compte : " + optionalCompte.get());

    //     if (optionalCompte.isEmpty()) {
    //         return ResponseHandler.error("Compte non trouvé", HttpStatus.NOT_FOUND);
    //     }
    //     CompteResponseDTO dto = compteMapper.toResponseDTO(optionalCompte.get());         
    //     return ResponseHandler.success("Compte récupéré avec succès", dto, HttpStatus.OK);
    // }

    // @GetMapping("/sole")
    // public ApiResponse<?> getMSolde(@AuthenticationPrincipal Compte compteConnecte) {
    //     if (compteConnecte == null) {
    //         return ApiResponse.error("Utilisateur non authentifié");
    //     }

    //     Double compte = compteService.calculerSolde(compteConnecte);

    //     return ApiResponse.success("Compte récupéré avec succès", transactionDTOs);
    // }

    @GetMapping("/me")
    public ApiResponse<?> getMonCompte(@AuthenticationPrincipal Compte compteConnecte) {
        if (compteConnecte == null) {
            return ApiResponse.error("Utilisateur non authentifié");
        }

        Compte compte = compteService.getCompteByTelephone(compteConnecte.getTelephone())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        CompteResponseDTO dto = compteMapper.toResponseDTO(compte);

        List<TranfertResponseDTO> transactionDTOs = transactionService.getTransactionsByCompte(compte);
        dto.setTransactions(transactionDTOs);

        return ApiResponse.success("Compte récupéré avec succès", dto);
    }

    @PostMapping
    public ApiResponse<?> createCompte(@Valid @RequestBody CompteRequestDTO dto)
    {
        Compte compte = compteMapper.toEntity(dto);
        Compte saved = compteService.createCompte(compte);
        CompteResponseDTO response = compteMapper.toResponseDTO(saved);
        return ApiResponse.success("Compte créé avec succès", response);
    }
}
