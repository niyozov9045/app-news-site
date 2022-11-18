package uz.pdp.appnewssite.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.FORBIDDEN)
@Data
public class ForbittenException extends RuntimeException {

    private String type;
    private String message;

    public ForbittenException(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
