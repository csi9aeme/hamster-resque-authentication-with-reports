package hamsterresqueauth.service;

import hamsterresqueauth.authentication.JwtService;
import hamsterresqueauth.dto.auth.AuthenticationRequestCommand;
import hamsterresqueauth.dto.auth.AuthenticationResponse;
import hamsterresqueauth.dto.auth.RegisterRequestCommand;
import hamsterresqueauth.enums.Authorities;
import hamsterresqueauth.model.Address;
import hamsterresqueauth.model.ContactInfo;
import hamsterresqueauth.model.LoginInfo;
import hamsterresqueauth.model.TemporaryHost;
import hamsterresqueauth.repository.TemporaryHostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TemporaryHostRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequestCommand requestCommand) {

        ContactInfo contactInfo = ContactInfo.builder()
                .email(requestCommand.getEmail())
                .phone(requestCommand.getPhone())
                .otherContactInfo(requestCommand.getOtherContactInfo())
                .build();

        Address address = Address.builder()
                .zip(requestCommand.getZip())
                .city(requestCommand.getCity())
                .street(requestCommand.getStreet())
                .houseNumber(requestCommand.getHouseNumber())
                .otherAddressInfo(requestCommand.getOtherAddressInfo())
                .build();

        LoginInfo loginInfo = LoginInfo.builder()
                .email(requestCommand.getEmail())
                .password(passwordEncoder.encode(requestCommand.getPassword()))
                .authorities(Authorities.USER)
                .build();

        TemporaryHost host = TemporaryHost.builder()
                .firstname(requestCommand.getFirstname())
                .lastname(requestCommand.getLastname())
                .loginInfo(loginInfo)
                .address(address)
                .contactInfo(contactInfo)
                .reports(new HashSet<>())
                .build();

        repository.save(host);

        String jwtToken = jwtService.generateToken(loginInfo);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequestCommand requestCommand) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestCommand.getEmail(),
                        requestCommand.getPassword())
        );

        TemporaryHost host = repository.findByEmail(requestCommand.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username."));

        String jwtToken = jwtService.generateToken(host.getLoginInfo());

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
