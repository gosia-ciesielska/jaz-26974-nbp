package pl.pjatk.jaz_26974_nbp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import pl.pjatk.jaz_26974_nbp.exception.InvalidRequestException;

import java.net.ConnectException;

@RestControllerAdvice
public class RequestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public String handleInvalidRequestException(InvalidRequestException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public String handleBadRequestException(HttpClientErrorException.BadRequest exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public String handleNotFoundException(HttpClientErrorException.NotFound exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(HttpServerErrorException.class)
    public void handleServerError() {

    }

    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(ConnectException.class)
    public void handleConnectException() {

    }
}
