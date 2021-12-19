package mongodb.service;

import lombok.RequiredArgsConstructor;
import mongodb.model.User;
import mongodb.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByFirstName(firstName);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found!!!");
        }
    }
}
