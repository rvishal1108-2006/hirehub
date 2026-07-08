package com.interview.hirehub.service;
import com.interview.hirehub.dto.request.CreateUserRequest;
import com.interview.hirehub.entity.User;
import com.interview.hirehub.entity.User;
import com.interview.hirehub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import com.interview.hirehub.dto.request.LoginRequest;
import com.interview.hirehub.dto.response.LoginResponse;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(CreateUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}