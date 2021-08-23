package zipFile.config.security;

import java.util.Date;

import com.auth0.jwt.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationTokenProvider {
    @Value("${user.password}")
    private String secret;

    public String generateJwtToken(UserPrincipal userPrincipal){
        return null;
    }
}
