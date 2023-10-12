package hamsterresqueauth.service;

import hamsterresqueauth.dto.user.UserRegisterCommand;
import hamsterresqueauth.dto.user.UserDto;

import java.util.Set;

public interface UserService {

    UserDto saveUser(UserRegisterCommand command);

    UserDto findUserByEmail(String email);

    Set<UserDto> findAllUsers();
}
