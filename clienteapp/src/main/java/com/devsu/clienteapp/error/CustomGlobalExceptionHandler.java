package com.devsu.clienteapp.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> constraintViolationException(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage().split("\\.")[1]);
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setPath(request.getDescription(false).split("\\=")[1]);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BussinessException.class})
    public ResponseEntity<BussinesCustomErrorResponse> customHandleBussinesException(BussinessException ex, WebRequest request) {
        BussinesCustomErrorResponse errors = new BussinesCustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setErrorCode(ex.getErrorCode());
        errors.setErrorMessage(ex.getErrorMessage());
        errors.setStatus(HttpStatus.CONFLICT.value());
        errors.setPath(request.getDescription(false).split("\\=")[1]);
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        List<String> requestValidErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(requestValidErrors.get(0));
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setPath(request.getDescription(false).split("\\=")[1]);

        return new ResponseEntity<>(errors, headers, status);

    }

    @Override
    protected ResponseEntity<Object>
    handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError("Revisar campos del request incorrectos: fechas y horas");
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setPath(request.getDescription(false).split("\\=")[1]);

        return new ResponseEntity<>(errors, headers, status);

    }

}