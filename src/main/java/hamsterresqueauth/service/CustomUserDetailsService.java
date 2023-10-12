package hamsterresqueauth.service;

import hamsterresqueauth.model.Authority;
import hamsterresqueauth.model.User;
import hamsterresqueauth.repository.AuthorityRepository;
import hamsterresqueauth.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getLoginInfo().getEmail(),
                    user.getLoginInfo().getPassword(),
                    mapRolesToAuthorities(user.getAuthorities()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> authorities) {
        Collection < ? extends GrantedAuthority> mapRoles = authorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
