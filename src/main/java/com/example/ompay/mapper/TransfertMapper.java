package com.example.ompay.mapper;

import com.example.ompay.dto.request.TransfertRequestDTO;
import com.example.ompay.dto.response.TranfertResponseDTO;
import com.example.ompay.entity.Transaction;
import com.example.ompay.entity.enums.TypeTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
// import org.mapstruct.Mappings;

import java.time.LocalDateTime;
// import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, TypeTransaction.class})
public interface TransfertMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compteSource", ignore = true)
    @Mapping(target = "compteDestination", ignore = true)
    @Mapping(target = "type", expression = "java(TypeTransaction.DEPOT)")
    @Mapping(target = "dateCreation", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "montant", ignore = true)
    @Mapping(target = "frais", ignore = true)
    @Mapping(target = "montantTotal", ignore = true)
    Transaction toEntity(TransfertRequestDTO dto);

    @Mapping(target = "destinataire", source = "compteDestination.telephone")
    TranfertResponseDTO toResponseDTO(Transaction transaction);
}