package com.Proyecto.Integrador.service;

import com.Proyecto.Integrador.exception.UserNotFoundException;
import com.Proyecto.Integrador.repository.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceMap implements UserService{
    private final Map<String, User> usersMap = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }
        usersMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(usersMap.get(id));
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public void deleteById(String id) {
        usersMap.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        if (!usersMap.containsKey(userId)) {
            throw new UserNotFoundException(userId);
        }
        user.setId(userId);
        return save(user);
    }
}
