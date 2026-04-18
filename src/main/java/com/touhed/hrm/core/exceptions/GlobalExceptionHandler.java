package com.touhed.hrm.core.exceptions;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.touhed.hrm.core.responses.ApiErrorResponse;
import org.apache.coyote.BadRequestException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import tools.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> buildErrorResponse( HttpStatus httpStatus, String message ) {
        Map<String, Object> response = new HashMap<>();
        response.put( "status", httpStatus.value() );
        response.put( "message", message );

        return new ResponseEntity<>( response, httpStatus );
    }

    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<Map<String, Object>> handleNotFoundException( NotFoundException ex ) {
        return buildErrorResponse( HttpStatus.NOT_FOUND, ex.getMessage() );
    }

    @ExceptionHandler( BadRequestException.class )
    public ResponseEntity<Map<String, Object>> handleBadRequestException( BadRequestException ex ) {
        return buildErrorResponse( HttpStatus.BAD_REQUEST, ex.getMessage() );
    }

//    @ExceptionHandler( BadCredentialsException.class )
//    public ResponseEntity<Map<String, Object>> handleBadCredentialsException( BadCredentialsException ex ) {
//        return buildErrorResponse( HttpStatus.BAD_REQUEST, ex.getMessage() );
//    }

//    @ExceptionHandler( JwtException.class )
//    public ResponseEntity<Map<String, Object>> handleJwtException( JwtException ex ) {
//        return buildErrorResponse( HttpStatus.FORBIDDEN, ex.getMessage() );
//    }

//    @ExceptionHandler( DisabledException.class )
//    public ResponseEntity<Map<String, Object>> handleDisabledException( DisabledException ex ) {
//        return buildErrorResponse( HttpStatus.FORBIDDEN, ex.getMessage() );
//    }

    @ExceptionHandler( AuthenticationException.class )
    public ResponseEntity<Map<String, Object>> handleAuthenticationException( AuthenticationException ex ) {
        return buildErrorResponse( HttpStatus.UNAUTHORIZED, ex.getMessage() );
    }

    @ExceptionHandler( SignatureException.class )
    public ResponseEntity<Map<String, Object>> handleSignatureException( SignatureException ex ) {
        return buildErrorResponse( HttpStatus.FORBIDDEN, ex.getMessage() );
    }


    @ExceptionHandler( IllegalStateException.class )
    public ResponseEntity<Map<String, Object>> handleIllegalState( IllegalStateException ex ) {
        return buildErrorResponse( HttpStatus.CONFLICT, ex.getMessage() );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException( Exception ex ) {
        ex.printStackTrace();
        return buildErrorResponse( HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred" );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<Map<String, Object>> handleValidationException( MethodArgumentNotValidException ex ) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map( DefaultMessageSourceResolvable::getDefaultMessage )
                .distinct()
                .collect( Collectors.joining( ". " ) ) + ".";

        return buildErrorResponse( HttpStatus.BAD_REQUEST, errorMessage );
    }

    @ExceptionHandler( HttpMessageNotReadableException.class )
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable( HttpMessageNotReadableException ex ) {
        if ( ex.getCause() instanceof InvalidFormatException ife ) {
            if ( ife.getTargetType().isEnum() ) {
                String enumType = ife.getTargetType().getSimpleName();
                String invalidValue = ife.getValue().toString();
                String message = "Invalid value '" + invalidValue + "' for enum " + enumType +
                        ". Please use one of: " + String.join( ", ",
                        java.util.Arrays.stream( ife.getTargetType().getEnumConstants() )
                                .map( Object::toString ).toList() );

                return buildErrorResponse( HttpStatus.BAD_REQUEST, message );
            }
        }

        return buildErrorResponse( HttpStatus.BAD_REQUEST, "Invalid Request Body" );
    }

    @ExceptionHandler( DataIntegrityViolationException.class )
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex ) {

        return buildErrorResponse( HttpStatus.BAD_REQUEST, ex.getMessage() );
    }
}

