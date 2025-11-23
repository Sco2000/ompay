package com.example.ompay.mapper;

import com.example.ompay.dto.request.PaiementRequestDTO;
import com.example.ompay.entity.Transaction;
import com.example.ompay.entity.enums.TypeTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, TypeTransaction.class})
public interface PaiementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compteSource", ignore = true)
    @Mapping(target = "compteDestination", ignore = true)
    @Mapping(target = "type", expression = "java(TypeTransaction.PAIEMENT)")
    @Mapping(target = "dateCreation", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "montant", source = "montant")
    @Mapping(target = "frais", ignore = true)
    @Mapping(target = "montantTotal", ignore = true)
    Transaction toEntity(PaiementRequestDTO dto);
}
