package io.github.igoroliveira7.libraryapi.service;


import io.github.igoroliveira7.libraryapi.model.Autor;
import io.github.igoroliveira7.libraryapi.model.GeneroLivro;
import io.github.igoroliveira7.libraryapi.model.Livro;
import io.github.igoroliveira7.libraryapi.repository.AutorRepository;
import io.github.igoroliveira7.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void updateWithoutUpdate() {
        var livro = livroRepository.findById(UUID.fromString(""))
                .orElse(null);
        livro.setDataPublicacao(LocalDate.of(1954, 7, 30));

        //livroRepository.save(livro);
    }

    @Transactional
    public void execute(){

        Autor autor = new Autor();
        autor.setNome("J.R.R Tolkien");
        autor.setNacionalidade("Inglesa");
        autor.setDataNascimento(LocalDate.of(1892, 3, 1));

        autorRepository.saveAndFlush(autor);


        Livro livro = new Livro();
        livro.setIsbn("9798525326225");
        livro.setPreco(BigDecimal.valueOf(83));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
        livro.setDataPublicacao(LocalDate.of(1954, 7, 29));

        livro.setAutor(autor);
        livroRepository.saveAndFlush(livro);

        if(autor.getNome().equals("J.R.R Tolkien")){
            throw new RuntimeException("Rollback!");
        }
    }
}
