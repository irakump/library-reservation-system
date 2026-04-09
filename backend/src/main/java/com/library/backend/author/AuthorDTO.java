package com.library.backend.author;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Integer authorId;
    private String firstName;
    private String lastName;

    public AuthorDTO(Author author) {
        this.authorId = author.getAuthorId();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
    }
}
