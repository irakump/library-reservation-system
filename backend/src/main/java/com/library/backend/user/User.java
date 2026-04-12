package com.library.backend.user;

import com.library.backend.book.Book;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity representing a user in the system.
 * Stores authentication data, profile information and relationships.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    /**
     * User email address (unique).
     */
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    /**
     * Display name of the user.
     */
    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    /**
     * Hashed password.
     */
    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    /**
     * Account creation timestamp.
     */
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    /**
     * User role defining access level.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * Favorite books of the user.
     * Many-to-many relationship with Book entity.
     * Manages favorites table
     */
    @ManyToMany
    @JoinTable(
            name = "favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "isbn")
    )
    private Set<Book> favorites = new HashSet<>();

    /**
     * Adds a book to user's favorites.
     *
     * @param book book to add
     */
    public void addFavorites(final Book book) {
        this.favorites.add(book);
    }

    /**
     * Removes a book from user's favorites.
     *
     * @param book book to remove
     */
    public void removeFavorites(final Book book) {
        this.favorites.remove(book);
    }

    /**
     * Returns favorite books.
     *
     * @return list of favorite books
     */
    public List<Book> getFavorites() {
        return List.copyOf(favorites);
    }


    /**
     * Creates a new user with default role and timestamp.
     *
     * @param userEmail user email
     * @param name display name
     * @param password hashed password
     */
    public User(final String userEmail,
                final String name,
                final String password) {
        this.email = userEmail;
        this.nickname = name;
        this.passwordHash = password;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.role = Role.user;
    }


    /**
     * Default constructor.
     */
    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String userEmail) {
        this.email = userEmail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String name) {
        this.nickname = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(final String password) {
        this.passwordHash = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Timestamp created) {
        this.createdAt = created;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role userRole) {
        this.role = userRole;
    }

    public enum Role {
        user, admin
    }
}
