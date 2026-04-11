package com.library.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Autowired
    TestEntityManager em;


    // Test create operation (insert into test database)
    @Test
    void shouldCreateNewUser() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        User insertedUser = repository.save(user);
        assertThat(em.find(User.class, insertedUser.getUserId())).isEqualTo(user);
    }

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

    // Test find all users
    @Test
    void shouldFindAllCreatedUsers() {
        User firstUser = new User("test@email.com", "FirstUser", "h8d6s6Gj!230Kh");
        User secondUser = new User("test2@email.com", "SecondUser", "h4shhhH");
        em.persist(firstUser);
        em.persist(secondUser);

        List<User> users = (List<User>) repository.findAll();

        // Collect user ids to list
        List<Integer> userIds = users.stream()
                                .map(User::getUserId)
                                .toList();

        assertTrue(userIds.contains(firstUser.getUserId()));
        assertTrue(userIds.contains(secondUser.getUserId()));
    }

    // Test find by id
    @Test
    void shouldFindUserById() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        em.persist(user);
        Optional<User> fetchedUser = repository.findById(user.getUserId());
        assertThat(fetchedUser).contains(user);
    }

    // Test find by id when user id does not exist
    @Test
    void shouldNotFindUserByNonExistingId() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        em.persist(user);
        int nonExistingId = user.getUserId() + 1;

        Optional<User> fetchedUser = repository.findById(nonExistingId);
        assertTrue(fetchedUser.isEmpty(), "Should not find user with non-existing id");
    }

    // Test delete operation
    @Test
    void shouldDeleteNewUser() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        em.persist(user);
        repository.delete(user);
        assertThat(em.find(User.class, user.getUserId())).isNull();
    }

}
