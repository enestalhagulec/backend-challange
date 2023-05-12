package net.etg.backendchallenge.exception.handling;

import net.etg.backendchallenge.exception.ErrorDetail;
import net.etg.backendchallenge.exception.NoCompanyException;
import net.etg.backendchallenge.exception.NoEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoCompanyException.class)
    public ResponseEntity<ErrorDetail> handleNoCompanyException(NoCompanyException exception,
                                                                WebRequest request){
        ErrorDetail error = new ErrorDetail();
        error.setCode("NO_COMPANY_ERR");
        error.setMessage(exception.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }

    @ExceptionHandler(NoEmployeeException.class)
    public ResponseEntity<ErrorDetail> handleNoEmployeeException(NoEmployeeException exception,
                                                                WebRequest request){
        ErrorDetail error = new ErrorDetail();
        error.setCode("NO_EMPLOYEE_ERR");
        error.setMessage(exception.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }
}
