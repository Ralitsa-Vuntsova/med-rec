package bg.sofia.uni.fmi.piss.project.medrec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class CouldNotDecodeException extends Exception {
    public CouldNotDecodeException(){
        super();
    }

    public CouldNotDecodeException(String message) {
        super(message);
    }
}