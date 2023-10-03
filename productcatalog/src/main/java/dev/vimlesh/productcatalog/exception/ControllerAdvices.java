package dev.vimlesh.productcatalog.exception;

import dev.vimlesh.productcatalog.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException exception){
        return new ResponseEntity(new ExceptionDto(HttpStatus.NOT_FOUND, exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
