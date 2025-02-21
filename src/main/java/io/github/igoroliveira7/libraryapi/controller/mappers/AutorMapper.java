package io.github.igoroliveira7.libraryapi.controller.mappers;


import io.github.igoroliveira7.libraryapi.controller.dto.AutorDTO;
import io.github.igoroliveira7.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
