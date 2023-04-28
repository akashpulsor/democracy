package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.exception.EmailExistsException;
import com.example.hackathon.exception.UserNameExistsException;
import com.example.hackathon.exceptions.TokenRefreshException;
import com.example.hackathon.model.ERole;
import com.example.hackathon.model.RefreshToken;
import com.example.hackathon.model.Role;
import com.example.hackathon.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserManagerImpl implements  UserManager{


    private final UserService userService;

    private final JwtService jwtService;

    private PasswordEncoder passwordEncoder;

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
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(),
                        loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Principal userDetails = (Principal) authentication.getPrincipal();
        String jwtToken  = this.jwtService.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = this.userService.createRefreshToken(userDetails.getId());

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setAccessToken(jwtToken);
        loginResponseDto.setRefreshToken(refreshToken.getToken());
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setUsername(userDetails.getUsername());
        userInfoResponse.setId(userDetails.getId());
        userInfoResponse.setEmail(userDetails.getEmail());
        userInfoResponse.setRoles(roles);
        loginResponseDto.setUserInfoResponse(userInfoResponse);
        return loginResponseDto;
    }



    @Override
    public UserDto getUserById(long id) {
        return userModelToDto(this.userService.findUserById(id));
    }

    @Override
    public LoginResponseDto logout() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle.toString() != "anonymousUser") {
            Long userId = ((Principal) principle).getId();
            this.userService.deleteByUserId(userId);
        }
        ResponseCookie jwtCookie = this.jwtService.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = this.jwtService.getCleanJwtRefreshCookie();
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setAccessToken(jwtCookie.toString());
        loginResponseDto.setRefreshToken(jwtRefreshCookie.toString());
        return loginResponseDto;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return this.userService.findByToken(refreshToken)
                    .map(this.userService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        String token =  this.jwtService.generateTokenFromUsername(user.getUserName());
                        TokenRefreshResponse response = new TokenRefreshResponse();
                        response.setAccessToken(token);
                        response.setRefreshToken(refreshToken);
                        return response;
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database!"));

        }
        throw  new TokenRefreshException(refreshToken, "Refresh token is not in database!");
    }


    private SearchResponseDto userToSearchResponseDto(User user){
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setEmail(user.getEmail());
        searchResponseDto.setUserName(user.getUserName());
        searchResponseDto.setId(user.getId());
        return searchResponseDto;
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

    @Override
    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        if(this.userService.findByEmail(signUpRequestDto.getEmail())) {
            throw  new EmailExistsException("Email Already exception");
        }

        if(this.userService.findByUsername(signUpRequestDto.getUsername())) {
            throw  new UserNameExistsException("User Name exists exception");
        }
        User user = singUpRequestDtoToUser(signUpRequestDto);
        Set<String> strRoles = signUpRequestDto.getRole();
        user.setRoles(getRoles(strRoles));
        this.userService.createUser(user);
        return userModelToDto(user);
    }

    private User singUpRequestDtoToUser(SignUpRequestDto signUpRequestDto){
        User user = new User();
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setNumber(signUpRequestDto.getPhone());
        user.setEmail(signUpRequestDto.getEmail());
        user.setUserName(signUpRequestDto.getUsername());
        return user;
    }


    private Set<Role> getRoles(Set<String> strRoles){
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = this.userService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = this.userService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = this.userService.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = this.userService.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

}
