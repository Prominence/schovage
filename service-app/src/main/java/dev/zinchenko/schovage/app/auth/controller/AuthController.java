package dev.zinchenko.schovage.app.auth.controller;

import dev.zinchenko.schovage.app.auth.dto.SignInRequest;
import dev.zinchenko.schovage.app.auth.dto.SignInResponse;
import dev.zinchenko.schovage.app.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signin")
    public SignInResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }
}
