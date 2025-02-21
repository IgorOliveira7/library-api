package io.github.igoroliveira7.libraryapi.controller.mappers;


import io.github.igoroliveira7.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.igoroliveira7.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.igoroliveira7.libraryapi.model.Livro;
import io.github.igoroliveira7.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}
