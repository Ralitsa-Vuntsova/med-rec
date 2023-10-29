package bg.sofia.uni.fmi.piss.project.medrec.service;

import bg.sofia.uni.fmi.piss.project.medrec.dto.LoginUserDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.LoginUserResponseDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.UserDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.UserRegistrationDto;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.UserAlreadyExistsException;
import bg.sofia.uni.fmi.piss.project.medrec.model.Type;
import bg.sofia.uni.fmi.piss.project.medrec.model.UserEntity;
import bg.sofia.uni.fmi.piss.project.medrec.repository.UserRepository;
import bg.sofia.uni.fmi.piss.project.medrec.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder encoder;

    public void registerPatient(UserRegistrationDto user) throws UserAlreadyExistsException {
        checkIfUserExists(user.getUsername());

        UserEntity userEntity = toUserEntity(user, Type.patient);
        userRepository.save(userEntity);
    }

    public LoginUserResponseDto login(LoginUserDto user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserEntity userDetails = (UserEntity) authentication.getPrincipal();

        return toLoginUserResponseDto(userDetails, jwt);
    }

    private void checkIfUserExists(String username) throws UserAlreadyExistsException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("There is already registered user with this username. Try with another.");
        }
    }

    private UserEntity toUserEntity(UserRegistrationDto user, Type type) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setType(type);

        return userEntity;
    }

    private LoginUserResponseDto toLoginUserResponseDto(UserEntity user, String jwt) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return new LoginUserResponseDto(userDto, jwt);
    }
}
