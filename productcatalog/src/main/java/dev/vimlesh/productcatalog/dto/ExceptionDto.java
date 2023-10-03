package dev.vimlesh.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    HttpStatus httpStatuscode;
    String message;

    public ExceptionDto(HttpStatus statusCode, String message){
        this.httpStatuscode = statusCode;
        this.message = message;
    }
}
