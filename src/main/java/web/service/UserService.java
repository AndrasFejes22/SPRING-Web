package web.service;

import web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import web.repository.UserRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /*
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setCreatedAt(ZonedDateTime.now());
        user.setStatus(request.getStatus());

        LOGGER.info("Creating new user: {}", user);
        return userRepository.save(user);
    }

     */
}
