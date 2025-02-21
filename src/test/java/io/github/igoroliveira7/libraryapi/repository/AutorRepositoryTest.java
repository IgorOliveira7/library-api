package io.github.igoroliveira7.libraryapi.repository;

import io.github.igoroliveira7.libraryapi.model.Autor;
import io.github.igoroliveira7.libraryapi.model.GeneroLivro;
import io.github.igoroliveira7.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Fiódor Dostoiévski");
        autor.setNacionalidade("Russa");
        autor.setDataNascimento(LocalDate.of(1822, 12, 16));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("17f01d87-49d6-4b70-9539-4bd4bf54b7fb");
        Optional<Autor> possivelAutor = repository.findById(id);

        Autor autorEncontrado = possivelAutor.get();
        if(possivelAutor.isPresent()) {
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);
            autorEncontrado.setDataNascimento(LocalDate.of(1821, 11, 11));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("1db9deae-d9be-4179-ad45-11678ea4a1c2");
        repository.deleteById(id);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Nelson Rodrigues");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1912,12,21));

        Livro livro = new Livro();
        livro.setIsbn("9758634937431");
        livro.setPreco(BigDecimal.valueOf(56));
        livro.setGenero(GeneroLivro.ENSAIO);
        livro.setTitulo("Livro Teste");
        livro.setDataPublicacao(LocalDate.of(1950, 12, 5));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("9748425937431");
        livro2.setPreco(BigDecimal.valueOf(60));
        livro2.setGenero(GeneroLivro.ENSAIO);
        livro2.setTitulo("Livro Teste Dois");
        livro2.setDataPublicacao(LocalDate.of(1959, 10, 4));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("5cc6d7b1-85b5-4d45-93a7-2097d8f9200d");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);
    }
}
