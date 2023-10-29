package bg.sofia.uni.fmi.piss.project.medrec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginUserDto {

    private String username;

    private String password;
}
