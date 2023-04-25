package com.example.hackathon.service;

import com.example.hackathon.dao.UserRepository;
import com.example.hackathon.dto.Principal;
import com.example.hackathon.dto.UserDto;
import com.example.hackathon.exceptions.UserNotFoundException;
import com.example.hackathon.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsersByEmail(String email) {
        return  this.userRepository.findByEmailStartsWith(email);
    }

    public User createUser(User user) {
        return  this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByUserName(username);
        if(optionalUser.isEmpty()){
            throw  new UsernameNotFoundException(username);
        }
        return new Principal(optionalUser.get());
    }

    public User findUserById(long id) {
        return this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

}
