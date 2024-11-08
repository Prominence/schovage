package dev.zinchenko.schovage.app.auth.service;

import dev.zinchenko.schovage.app.auth.dto.SignInRequest;
import dev.zinchenko.schovage.app.auth.dto.SignInResponse;
import dev.zinchenko.schovage.app.user.entity.User;
import dev.zinchenko.schovage.app.user.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenService tokenService, @Lazy AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username).orElseThrow();
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password());
        Authentication authenticated = authenticationManager.authenticate(usernamePassword);
        User user = (User) authenticated.getPrincipal();
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        return new SignInResponse(accessToken, refreshToken, tokenService.getAccessTokenExpiresIn(), tokenService.getRefreshTokenExpiresIn());
    }
}
