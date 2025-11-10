package com.example.ompay.mapper;

import com.example.ompay.dto.request.UtilisateurRequestDTO;
import com.example.ompay.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreation", expression = "java(java.time.LocalDateTime.now())")
    Utilisateur toEntity(UtilisateurRequestDTO dto);
}
