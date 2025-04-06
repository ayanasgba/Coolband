package kg.geeks.coolband.exceptions.handler;

import kg.geeks.coolband.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionResponse handleRuntimeException(RuntimeException ex) {
        return new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getClass().getSimpleName(),
                ex.getMessage()
        );
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ExceptionHandler(BadCredentialException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse badCredentialForbidden(BadCredentialException e) {
        return new ExceptionResponse(
                HttpStatus.FORBIDDEN,
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse alreadyExistException(AlreadyExistException e) {
        return new ExceptionResponse(
                HttpStatus.CONFLICT,
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse resourceNotFound(ResourceNotFoundException e) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage());
    }
}
