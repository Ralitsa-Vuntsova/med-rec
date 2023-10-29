package bg.sofia.uni.fmi.piss.project.medrec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserResponseDto {
    private UserDto user;

    private String token;
}
