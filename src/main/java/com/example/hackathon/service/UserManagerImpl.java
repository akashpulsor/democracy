package com.example.hackathon.service;

import com.example.hackathon.dto.LoginRequestDto;
import com.example.hackathon.dto.LoginResponseDto;
import com.example.hackathon.dto.SearchResponseDto;
import com.example.hackathon.dto.UserDto;
import com.example.hackathon.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserManagerImpl implements  UserManager{


    private final UserService userService;

    private final JwtService jwtService;

    private PasswordEncoder passwordEncoder;
    // injecting authentication manager

    private final AuthenticationManager authenticationManager;

    public UserManagerImpl(UserService userService,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<SearchResponseDto> getUsersByEmail(String email) {
        log.info("Searching for email - {}",email);

        List<SearchResponseDto> searchResponseDtoList = new ArrayList<>();
        for(User user: this.userService.getUsersByEmail(email)){
            SearchResponseDto searchResponseDto = this.userToSearchResponseDto(user);
            searchResponseDtoList.add(searchResponseDto);
        }
        return searchResponseDtoList;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        return userModelToDto(this.userService.createUser(userDtoToModel(userDto)));
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // Creating UsernamePasswordAuthenticationToken object
        // to send it to authentication manager.
        // Attention! We used two parameters constructor.
        // It sets authentication false by doing this.setAuthenticated(false);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(),
                loginRequestDto.getPassword());
        // we let the manager do its job.
        authenticationManager.authenticate(token);
        // if there is no exception thrown from authentication manager,
        // we can generate a JWT token and give it to user.
        String jwt = this.jwtService.generate(loginRequestDto.getUserName());

        return loginResponseDto(jwt);
    }

    @Override
    public UserDto getUserById(long id) {
        return userModelToDto(this.userService.findUserById(id));
    }


    private SearchResponseDto userToSearchResponseDto(User user){
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setEmail(user.getEmail());
        searchResponseDto.setUserName(user.getUserName());
        searchResponseDto.setId(user.getId());
        return searchResponseDto;
    }
    private LoginResponseDto loginResponseDto(String jwt){
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setAuthToken(jwt);
        return loginResponseDto;
    }
    private UserDto userModelToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setNumber(user.getNumber());
        userDto.setPassword(user.getPassword());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setFollowerCount(user.getFollowerCount());
        return userDto;
    }

    private User userDtoToModel(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setNumber(userDto.getNumber());
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
