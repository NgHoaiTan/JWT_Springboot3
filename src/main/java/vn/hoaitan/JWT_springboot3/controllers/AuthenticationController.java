package vn.hoaitan.JWT_springboot3.controllers;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hoaitan.JWT_springboot3.entity.User;
import vn.hoaitan.JWT_springboot3.models.LoginResponse;
import vn.hoaitan.JWT_springboot3.models.LoginUserModel;
import vn.hoaitan.JWT_springboot3.models.RegisterUserModel;
import vn.hoaitan.JWT_springboot3.services.AuthenticationService;
import vn.hoaitan.JWT_springboot3.services.JwtService;

@RequestMapping ("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    public AuthenticationController(final JwtService jwtService, final AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<User> authenticate(@RequestBody RegisterUserModel registerUser) {
        User registeredUser = authenticationService.signup(registerUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(path = "/login")
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) {
        User authenticatedUser = authenticationService.authenticate(loginUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }


}