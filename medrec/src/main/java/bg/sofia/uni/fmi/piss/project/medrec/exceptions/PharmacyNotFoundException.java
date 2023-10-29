package bg.sofia.uni.fmi.piss.project.medrec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PharmacyNotFoundException extends Exception {

    public PharmacyNotFoundException(){
            super();
        }

    public PharmacyNotFoundException(String message) {
            super(message);
        }
}
