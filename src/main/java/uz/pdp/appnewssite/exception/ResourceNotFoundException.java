package uz.pdp.appnewssite.exception;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName; // role tableni topolmadim
    private final String resourceField; // name
    private final Object object; // USER, ADMIN


}
