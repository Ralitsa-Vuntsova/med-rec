package bg.sofia.uni.fmi.piss.project.medrec.dto;

import bg.sofia.uni.fmi.piss.project.medrec.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    private Type type;

    private String name;

    private String address;
}
