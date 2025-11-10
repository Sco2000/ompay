package com.example.ompay.mapper;

import com.example.ompay.dto.request.CompteRequestDTO;
import com.example.ompay.dto.response.CompteResponseDTO;
import com.example.ompay.entity.Compte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface CompteMapper {

    CompteMapper INSTANCE = Mappers.getMapper(CompteMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreation", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "statut", expression = "java(com.example.ompay.entity.enums.StatutCompte.ACTIF)")
    @Mapping(target = "solde", ignore = true)
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "motifSuspension", ignore = true)
    @Mapping(target = "codeMarchand", ignore = true)
    @Mapping(target = "typeClient", expression = "java(com.example.ompay.entity.enums.TypeClient.CLIENT)")
    @Mapping(target = "codeConnexion", source = "codeConnexion")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "utilisateur", source = "utilisateur")
    Compte toEntity(CompteRequestDTO dto);

    @Mapping(target = "utilisateurId", source = "utilisateur.id")
    @Mapping(target = "prenom", source = "utilisateur.prenom")
    @Mapping(target = "nom", source = "utilisateur.nom")
    CompteResponseDTO toResponseDTO(Compte compte);
}
