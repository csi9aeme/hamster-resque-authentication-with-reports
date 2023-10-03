package hamsterresqueauth.controller;

import hamsterresqueauth.dto.auth.AuthenticationRequestCommand;
import hamsterresqueauth.dto.auth.AuthenticationResponse;
import hamsterresqueauth.dto.auth.RegisterRequestCommand;
import hamsterresqueauth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequestCommand requestCommand) {

        return ResponseEntity.ok(service.register(requestCommand));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequestCommand requestCommand) {
        return ResponseEntity.ok(service.authenticate(requestCommand));
    }
}
