package hamsterresqueauth.service;

import hamsterresqueauth.dto._etc.ContactInfoDto;
import hamsterresqueauth.dto.user.UserDto;
import hamsterresqueauth.dto.user.UserRegisterCommand;
import hamsterresqueauth.exception.UsernameAlreadyExistException;
import hamsterresqueauth.mapper.UserMapper;
import hamsterresqueauth.model.*;
import hamsterresqueauth.model._embedded.Address;
import hamsterresqueauth.model._embedded.ContactInfo;
import hamsterresqueauth.model._embedded.LoginInfo;
import hamsterresqueauth.repository.AuthorityRepository;
import hamsterresqueauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;


    @Override
    public UserDto saveUser(UserRegisterCommand registerCommand) throws UsernameAlreadyExistException{

        if(userRepository.findByEmail(registerCommand.getEmail()).isPresent()) {
            throw new UsernameAlreadyExistException();
        }

        ContactInfo contactInfo = ContactInfo.builder()
                .email(registerCommand.getEmail())
                .phone(registerCommand.getPhone())
                .otherContactInfo(registerCommand.getOtherContactInfo())
                .build();

        Address address = Address.builder()
                .zip(registerCommand.getZip())
                .city(registerCommand.getCity())
                .street(registerCommand.getStreet())
                .houseNumber(registerCommand.getHouseNumber())
                .otherAddressInfo(registerCommand.getOtherAddressInfo())
                .build();

        LoginInfo loginInfo = LoginInfo.builder()
                .email(registerCommand.getEmail())
                .password(passwordEncoder.encode(registerCommand.getPassword()))
                .build();

        Authority authority = authorityRepository.findByName("ROLE_ADMIN");
        if(authority == null){
            authority = checkRoleExist();
        }

        User user = User.builder()
                .firstname(registerCommand.getFirstname())
                .lastname(registerCommand.getLastname())
                .loginInfo(loginInfo)
                .address(address)
                .contactInfo(contactInfo)
                .authorities(Arrays.asList(authority))
                .reports(new HashSet<>())
                .build();

        userRepository.save(user);

        return userMapper.toUserDto(user);

    }
// Maybe will useful
//    private Address buildAddress(String zip, String city, String street, String houseNumber, String other) {
//       return new Address(zip, city, street, houseNumber, other);
//    }


    @Override
    public UserDto findUserByEmail(String email) {
        return userMapper.toUserDto(findUserEntityByEmail(email));
    }

    @Override
    public Set<UserDto> findAllUsers() {
        Set<User> users = new HashSet<>(userRepository.findAll());
        return userMapper.toUserDto(users);
    }


    private User findUserEntityByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(email));
    }

    private Authority checkRoleExist(){
        Authority authority = new Authority();
        authority.setName("ROLE_ADMIN");
        return authorityRepository.save(authority);
    }

}
