package com.example.hackathon.service;

import com.example.hackathon.dao.RefreshTokenRepository;
import com.example.hackathon.dao.RoleRepository;
import com.example.hackathon.dao.UserRepository;
import com.example.hackathon.dto.Principal;
import com.example.hackathon.exceptions.TokenRefreshException;
import com.example.hackathon.exceptions.UserNotFoundException;
import com.example.hackathon.model.ERole;
import com.example.hackathon.model.RefreshToken;
import com.example.hackathon.model.Role;
import com.example.hackathon.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {


    @Value("${propaganda.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final UserRepository userRepository;


    private final RefreshTokenRepository refreshTokenRepository;

    private final RoleRepository roleRepository;

    public  UserService(UserRepository userRepository,
                        RefreshTokenRepository refreshTokenRepository,
                        RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.roleRepository = roleRepository;
    }


    public List<User> getUsersByEmail(String email) {
        return  this.userRepository.findByEmailStartsWith(email);
    }

    public User createUser(User user) {
        return  this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username).
                orElseThrow(()-> new UserNotFoundException("User Not Found with username: "+ username));

        return Principal.build(user);
    }

    public User findUserById(long id) {
        return this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }


    public boolean findByUsername(String username) {
        return this.userRepository.existsByUserName(username);
    }

   public boolean findByEmail(String email) {
        return this.userRepository.existsByEmail(email);
   }

   public Optional<Role> findByName(ERole role) {
        return this.roleRepository.findByName(role);
   }



}
