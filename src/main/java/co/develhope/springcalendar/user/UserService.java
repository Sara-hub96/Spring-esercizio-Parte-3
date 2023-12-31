package co.develhope.springcalendar.user;

import co.develhope.springcalendar.user.User;
import co.develhope.springcalendar.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> viewOneUser(long id) {
        return userRepository.findById(id);
    }

    public List<User> viewAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(long id, User user) {
        return userRepository.findById(id)
                .map(user1 -> {
                    user1.setFullName(user.getFullName());
                    user1.setEmail(user.getEmail());

                    return userRepository.save(user1);
                });
    }
}
