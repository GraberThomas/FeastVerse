package graber.thomas.FeastVerse.service;
import graber.thomas.FeastVerse.model.User;
import graber.thomas.FeastVerse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> get(UUID id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        // -- In our context, the username represent username
        // -- But in spring security username can be username, id, pseudo, phone number and so on...
        if(username.contains("@")) {
            return Optional.ofNullable(this.userRepository.findUserByEmail(username));
        }else{
            return Optional.ofNullable(this.userRepository.findUserByPseudo(username));
        }
    }

    @Override
    public Optional<User> create(User user) {
        // -- Set create and update date to now
        user.setCreatedDate(LocalDate.now());
        user.setUpdatedDate(LocalDate.now());
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User> update(User user) {
        // -- Set user update date to now
        user.setUpdatedDate(LocalDate.now());
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }
}
