package io.github.toquery.example.spring.sharding.sphere.jpa.modules.user.service;

import io.github.toquery.example.spring.sharding.sphere.modules.user.User;
import io.github.toquery.example.spring.sharding.sphere.jpa.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User save() {
        User user = new User();
        user.setUsername("toquery");
        user.setPwd("toquery");
        return userRepository.saveAndFlush(user);
    }

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
