package hamsterresqueauth.controller;

import hamsterresqueauth.dto.user.UserDto;
import hamsterresqueauth.dto.user.UserRegisterCommand;
import hamsterresqueauth.service.UserService;
import hamsterresqueauth.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class ControllerForTest {

    private final UserServiceImp userService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserRegisterCommand registerCommand) {
        return userService.saveUser(registerCommand);
    }
}
