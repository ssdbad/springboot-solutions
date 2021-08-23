package zipFile.config.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import zipFile.exception.ExceptionResponse;

@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.UNAUTHORIZED.getReasonPhrase().toUpperCase(),
         AuthenticationConstant.ACCESS_DENIED_MESSAGE, HttpStatus.UNAUTHORIZED.value());
         OutputStream outputStream = response.getOutputStream();
         ObjectMapper mapper = new ObjectMapper();
         mapper.writeValue(outputStream, exceptionResponse);
         outputStream.flush();
    }
    
}
