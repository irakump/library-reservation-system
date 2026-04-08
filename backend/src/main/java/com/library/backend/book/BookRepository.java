
package com.library.backend.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    Iterable<Book> findByGenre(String genre);
    Iterable<Book> findByYear(int year);
    Iterable<Book> findByLanguage(String language);
}
