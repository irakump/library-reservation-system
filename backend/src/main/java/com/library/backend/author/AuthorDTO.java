package com.library.backend.author;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    /** Unique identifier for the author. */
    private Integer authorId;
    /** Author's first name. */
    private String firstName;
    /** Author's last name. */
    private String lastName;

    /**
     * Constructor from Author entity.
     * @param author the Author entity
     */
    public AuthorDTO(final Author author) {
        this.authorId = author.getAuthorId();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
    }
}
