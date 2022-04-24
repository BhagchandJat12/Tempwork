package com.user.user.User.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<Object> emailExist(MyException exception, WebRequest request) {
        Error error = new Error(new Date(), exception.getMessage(), request.getDescription(false));
        if (exception.getMessage().equals("Email Already Exist"))
            return new ResponseEntity<>(error, HttpStatus.ALREADY_REPORTED);
        else if (exception.getMessage().equals("Enter valid Password"))
            return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    class Error {
        private Date timestamp;
        private String message;
        private String details;

        public Error(Date timestamp, String message, String details) {
            this.timestamp = timestamp;
            this.message = message;
            this.details = details;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        @Override
        public String toString() {
            return "Error [details=" + details + ", message=" + message + ", timestamp=" + timestamp + "]";
        }

    }
}
