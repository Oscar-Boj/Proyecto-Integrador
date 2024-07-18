package com.Proyecto.Integrador.controller.user;

import com.Proyecto.Integrador.dto.UserDto;
import com.Proyecto.Integrador.exception.UserNotFoundException;
import com.Proyecto.Integrador.repository.User;
import com.Proyecto.Integrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService usersService;

    public UserController(@Autowired UserService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        User createdUser = usersService.save(user);
        URI createdUserUri = URI.create("/users/" + createdUser.getId());
        return ResponseEntity.created(createdUserUri).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.all();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        User user = usersService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        User existingUser = usersService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.update(userDto);
        User updatedUser = usersService.save(existingUser);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        User existingUser = usersService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
