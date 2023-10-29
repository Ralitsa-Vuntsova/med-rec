package bg.sofia.uni.fmi.piss.project.medrec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ExternalServiceNotAvailableException extends Exception {
    public ExternalServiceNotAvailableException(){
        super();
    }

    public ExternalServiceNotAvailableException(String message) {
        super(message);
    }
}