package io.github.igoroliveira7.libraryapi.controller.mappers;

import io.github.igoroliveira7.libraryapi.controller.dto.UsuarioDTO;
import io.github.igoroliveira7.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
