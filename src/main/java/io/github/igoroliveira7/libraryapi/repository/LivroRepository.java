package io.github.igoroliveira7.libraryapi.repository;

import io.github.igoroliveira7.libraryapi.model.Autor;
import io.github.igoroliveira7.libraryapi.model.GeneroLivro;
import io.github.igoroliveira7.libraryapi.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listAllByTitleAndPrice();

    @Query("select a from Livro l join l.autor a")
    List<Autor> listAutorByBook();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listGenreByBrazilianAutors();


    // named parameters
    @Query("select l from Livro l where l.genero = :genero order by :paramOrdem")
    List<Livro> findByGenre(
            @Param("genero") GeneroLivro generoLivro,
            @Param("paramOrdem") String namePropertie);

    // positional parameters
    @Query("select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByGenreInPositionalParameter(
            String namePropertie,
            GeneroLivro generoLivro);

    @Modifying
    @Transactional
    @Query("delete from Livro where genero = ?1")
    void deleteByGenre(GeneroLivro genero);

    boolean existsByAutor(Autor autor);

    //@Modifying
    //@Transactional
    //@Query("update Livro set dataPublicacao = ?1 where dataPublicacao = '1950-12-5'")
    //void updateDatePublication(LocalDate newDate);
}
