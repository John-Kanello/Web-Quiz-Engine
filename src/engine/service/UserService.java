package engine.service;

import engine.model.entity.User;
import engine.model.dto.request.UserDto;
import engine.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static engine.service.UserService.UserRegistrationStatus.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserRegistrationStatus register(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) {
            return ALREADY_REGISTERED;
        }
        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return NEWLY_CREATED;
    }

    public enum UserRegistrationStatus {
        ALREADY_REGISTERED,
        NEWLY_CREATED
    }
}
