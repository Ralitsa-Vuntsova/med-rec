package bg.sofia.uni.fmi.piss.project.medrec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MedicineNotFoundException extends Exception {

    public MedicineNotFoundException(){
        super();
    }

    public MedicineNotFoundException(String message) {
        super(message);
    }
}
