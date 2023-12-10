package co.develhope.springcalendar.user;

import co.develhope.springcalendar.user.User;
import co.develhope.springcalendar.user.UserService;
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
    public User createNewUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
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
    public String deleteOneEvent(@PathVariable long id) {
        userService.deleteUser(id);
        return String.format("User with id %d has been deleted!", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateOneUser(@PathVariable long id, @RequestBody User user) {
        Optional<User> modifiedUser = userService.updateUser(id, user);
        if (modifiedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modifiedUser.get());
    }
}