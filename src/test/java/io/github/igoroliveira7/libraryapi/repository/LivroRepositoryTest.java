package io.github.igoroliveira7.libraryapi.repository;


import io.github.igoroliveira7.libraryapi.model.Autor;
import io.github.igoroliveira7.libraryapi.model.GeneroLivro;
import io.github.igoroliveira7.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("9783127323207");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Os Irmãos Karamazov");
        livro.setDataPublicacao(LocalDate.of(1867,11, 15));


        Autor autor = autorRepository.findById(UUID.fromString("17f01d87-49d6-4b70-9539-4bd4bf54b7fb")).orElse(null);
        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("9788535929225");
        livro.setPreco(BigDecimal.valueOf(83));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Anna Karenina");
        livro.setDataPublicacao(LocalDate.of(1877, 2, 16));

        Autor autor = new Autor();
        autor.setNome("Liev Tolstói");
        autor.setNacionalidade("Russa");
        autor.setDataNascimento(LocalDate.of(1828, 9, 9));

        autorRepository.save(autor);
        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("9788534932431");
        livro.setPreco(BigDecimal.valueOf(83));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Guerra e Paz");
        livro.setDataPublicacao(LocalDate.of(1867, 1, 3));

        Autor autor = new Autor();
        autor.setNome("Liev Tolstói");
        autor.setNacionalidade("Russa");
        autor.setDataNascimento(LocalDate.of(1828, 9, 9));

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro()
    {
        UUID id = UUID.fromString("");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);
        repository.save(livroParaAtualizar);
    }

    /*@Test
    void deletar() {
        UUID id = UUID.fromString("");
        repository.deleteById(id);
    }*/

    @Test
    //@Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("b64f808d-019c-4527-9419-cb2c639d39eb");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Os Irmãos Karamazov");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest() {
        Optional<Livro> livro = repository.findByIsbn("9788534932431");
        livro.ifPresent(System.out::println);
    }

    @Test
    void listBookWithQueryJPQL() {
        var result = repository.listAllByTitleAndPrice();
        result.forEach(System.out::println);
    }

    @Test
    void listAutorOfBooks() {
        var result = repository.listAutorByBook();
        result.forEach(System.out::println);
    }

    @Test
    void listAutorByGenreNacionalityBrazil() {
        var result = repository.listGenreByBrazilianAutors();
        result.forEach(System.out::println);
    }

    @Test
    void listBooksByGenre() {
        var result = repository.findByGenre(GeneroLivro.ROMANCE, "dataPublicacao");
        result.forEach(System.out::println);
    }

    @Test
    void listBooksByGenrePositionalParam() {
        var result = repository.findByGenreInPositionalParameter("dataPublicacao", GeneroLivro.ENSAIO);
        result.forEach(System.out::println);
    }

    @Test
    void deleteByGenreTest() {
        repository.deleteByGenre(GeneroLivro.ENSAIO);
    }

    //@Test
    //void updateDatePublication() {
        //repository.updateDatePublication(LocalDate.of(1899, 1, 2));
    //}
}