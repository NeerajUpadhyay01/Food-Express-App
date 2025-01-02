package com.example.food_express_app.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.food_express_app.entities.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserId() {
        User user = new User();
        user.setName("testUser");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUserId(user.getUserId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("testUser");
    }

    @Test
    public void testFindByNameAndPassword() {
        User user = new User();
        user.setName("testUser");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByNameAndPassword("testUser", "password");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("testUser");
    }

    @Test
    public void testFindByName() {
        User user = new User();
        user.setName("testUser");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByName("testUser");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("testUser");
    }
}
