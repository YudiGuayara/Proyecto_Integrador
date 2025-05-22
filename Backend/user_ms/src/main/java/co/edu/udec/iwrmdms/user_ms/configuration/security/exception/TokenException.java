package co.edu.udec.iwrmdms.user_ms.configuration.security.exception;

import javax.naming.AuthenticationException;

public class TokenException extends AuthenticationException {
    public TokenException(){super("A problem with the token has ocurred");}
}
