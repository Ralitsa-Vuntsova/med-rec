package bg.sofia.uni.fmi.piss.project.medrec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DrugAlreadyExistsException extends Exception {

    public DrugAlreadyExistsException(){
        super();
    }

    public DrugAlreadyExistsException(String message) {
        super(message);
    }

}