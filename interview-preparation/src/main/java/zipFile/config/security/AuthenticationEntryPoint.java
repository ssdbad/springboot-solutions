package zipFile.config.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import zipFile.exception.ExceptionResponse;

@Component
public class AuthenticationEntryPoint extends Http403ForbiddenEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
            throws IOException {
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), AuthenticationConstant.FORBIDDEN_MESSAGE,
                 HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(), HttpStatus.FORBIDDEN.value());

                 response.setStatus(HttpStatus.FORBIDDEN.value());
                 OutputStream outputStream = response.getOutputStream();
                 ObjectMapper mapper = new ObjectMapper();
                 mapper.writeValue(outputStream, exceptionResponse);
                 outputStream.flush();
    }
}
