package bg.sofia.uni.fmi.piss.project.medrec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DrugNotFoundException extends Exception {
    public DrugNotFoundException(){
        super();
    }

    public DrugNotFoundException(String message) {
        super(message);
    }
}
