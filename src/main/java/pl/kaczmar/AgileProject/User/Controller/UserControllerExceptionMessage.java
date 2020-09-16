package pl.kaczmar.AgileProject.User.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kaczmar.AgileProject.User.Exception.AgileException;

@ControllerAdvice
public class UserControllerExceptionMessage {

    @ExceptionHandler(AgileException.class)
    public ResponseEntity<String> getAgileExceptionMessage(AgileException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
