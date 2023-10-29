package bg.sofia.uni.fmi.piss.project.medrec.contoller;

import bg.sofia.uni.fmi.piss.project.medrec.dto.LoginUserDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.LoginUserResponseDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.UserRegistrationDto;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.UserAlreadyExistsException;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.UserNotFoundException;
import bg.sofia.uni.fmi.piss.project.medrec.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerPatient(@RequestBody UserRegistrationDto userRegistrationDto) throws UserAlreadyExistsException {
        authenticationService.registerPatient(userRegistrationDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginUserResponseDto loginUser(@RequestBody LoginUserDto loginUserDto) throws UserNotFoundException {
        return authenticationService.login(loginUserDto);
    }
}
