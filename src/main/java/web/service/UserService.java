package web.service;

import web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        LOGGER.info("UserService created");
    }

    public User getUser(String name) {
        return new User(Optional.ofNullable(name).orElse("John Doe"));
    }
}