package com.receipt.golbalExceptionHandler;

import com.receipt.exception.IdNotFoundException;
import com.receipt.exception.InvalidIdException;
import com.receipt.exception.InvalidReciptException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalHandler {
    @ResponseStatus(NOT_FOUND)
   @ExceptionHandler(IdNotFoundException.class)
    public @ResponseBody HttpErrorInfo handleIdNotFoundException(HttpServletRequest request, IdNotFoundException ex) {
        return   createHttpErrorInfo(NOT_FOUND, request, ex);
    }
    @ExceptionHandler(Exception.class)
    public @ResponseBody HttpErrorInfo otherExceptionHandler(HttpServletRequest request, Exception ex) {
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST,request,  ex);
    }

    @ExceptionHandler(InvalidIdException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody HttpErrorInfo handleInvalidId(HttpServletRequest request, InvalidIdException ex) {
        return createHttpErrorInfo(BAD_REQUEST, request, ex);
    }
    @ExceptionHandler(InvalidReciptException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody HttpErrorInfo handleInvalidReceipt(HttpServletRequest request, InvalidReciptException ex) {
        return createHttpErrorInfo(BAD_REQUEST, request, ex);
    }



    private HttpErrorInfo createHttpErrorInfo(
            HttpStatus httpStatus, HttpServletRequest request, Exception ex) {

        final String path = request.getRequestURL().toString();
        final String message = ex.getMessage();

        return new HttpErrorInfo(httpStatus, path, message);
    }
}
