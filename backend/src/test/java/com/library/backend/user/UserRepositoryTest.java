package com.library.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Autowired
    TestEntityManager em;

    // Test update operation
    @Test
    void shouldUpdateUserNickName() {
        User newUser = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        em.persist(newUser);

        // Set new nickname
        String newNickname = "NewNickname";
        newUser.setNickname(newNickname);
        repository.save(newUser);

        User updatedUser = em.find(User.class, newUser.getUserId());
        assertThat(updatedUser.getNickname()).isEqualTo(newNickname);

    }
}
