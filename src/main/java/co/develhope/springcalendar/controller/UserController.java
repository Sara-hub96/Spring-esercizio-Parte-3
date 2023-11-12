package co.develhope.springcalendar.controller;

import co.develhope.springcalendar.model.User;
import co.develhope.springcalendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public String createNewUser(@RequestBody User user) {
        userService.createUser(user);
        return "A new event has been created!";
    }

    @GetMapping
    public List<User> viewAllUsers() {
        return userService.viewAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> viewEvent(@PathVariable long id) {
        Optional<User> user = userService.viewOneUser(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOneEvent(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateOneUser(@PathVariable long id, @RequestBody User user) {
        User modifiedUser = userService.updateUser(id, user);
        if (modifiedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modifiedUser);
    }
}