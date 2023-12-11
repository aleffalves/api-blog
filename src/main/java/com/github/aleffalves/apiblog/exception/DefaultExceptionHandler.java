package com.github.aleffalves.apiblog.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.aleffalves.apiblog.utils.ConstantsUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {
        ProblemDetail errorDetail = null;

        if (ex instanceof BadCredentialsException || ex.getMessage().equals(ConstantsUtils.ACCESS_DENIED)) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty(ConstantsUtils.ACCESS_DENIED, "Authentication Failure");
        }else if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty(ConstantsUtils.ACCESS_DENIED, "not_authorized!");

        }else if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty(ConstantsUtils.ACCESS_DENIED, "JWT Signature not valid");
        } else if (ex instanceof TokenExpiredException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty(ConstantsUtils.ACCESS_DENIED, "JWT Token expired.");
        }

        return errorDetail;
    }

}
