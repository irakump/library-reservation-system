package com.library.backend.user;

import com.library.backend.book.Book;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    //manages favorites table
    @ManyToMany
    @JoinTable(
            name = "favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "isbn")
    )
    private Set<Book> favorites = new HashSet<>();

    public void addFavorites(Book book) {
        this.getFavorites().add(book);
    }

    public void removeFavorites(Book book) {
        this.getFavorites().remove(book);
    }

    public Set<Book> getFavorites() {
        return favorites;
    }


    public User(String email, String nickname, String passwordHash) {
        this.email = email;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.role = Role.user;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        user, admin
    }
}
