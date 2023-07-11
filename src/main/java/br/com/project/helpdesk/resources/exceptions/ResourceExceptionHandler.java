package br.com.project.helpdesk.resources.exceptions;

import br.com.project.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.project.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandardError> objectnotFoundException(ObjectnotFoundException ex, HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object Not Found", ex.getMessage(), request.getRequestURI());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Violação de dados", ex.getMessage(), request.getRequestURI());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException ex, HttpServletRequest request){

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation Error", "Erro na validação dos campos", request.getRequestURI());

        for(FieldError x : ex.getBindingResult().getFieldErrors()){
            errors.addErrors(x.getField(), x.getDefaultMessage());
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> validationError(ConstraintViolationException ex, HttpServletRequest request){

        /*String erro = ex.getMessage();

        int startIndexFieldName = erro.indexOf("propertyPath=") + "propertyPath=".length();
        int endIndexFieldName = erro.indexOf(",", startIndexFieldName);
        String fieldName = erro.substring(startIndexFieldName, endIndexFieldName);

        int startIndexMessage = erro.indexOf("interpolatedMessage='") + "interpolatedMessage='".length();
        int endIndexMessage = erro.indexOf("'", startIndexMessage);
        String message = erro.substring(startIndexMessage, endIndexMessage);

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation Error", "Erro na validação dos campos", request.getRequestURI());

        errors.addErrors(fieldName, message);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);*/

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Número do registro de contribuinte individual brasileiro (CPF) inválido!",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
