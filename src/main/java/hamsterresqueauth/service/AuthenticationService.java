package hamsterresqueauth.service;

import hamsterresqueauth.authentication.JwtService;
import hamsterresqueauth.dto.auth.AuthenticationRequestCommand;
import hamsterresqueauth.dto.auth.AuthenticationResponse;
import hamsterresqueauth.dto.auth.RegisterRequestCommand;
import hamsterresqueauth.enums.Role;
import hamsterresqueauth.model.User;
import hamsterresqueauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequestCommand requestCommand) {
        User user = User.builder()
                .firstname(requestCommand.getFirstname())
                .lastname(requestCommand.getLastname())
                .email(requestCommand.getEmail())
                .password(passwordEncoder.encode(requestCommand.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequestCommand requestCommand) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestCommand.getEmail(),
                        requestCommand.getPassword())
        );

        User user = repository.findByEmail(requestCommand.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username."));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
