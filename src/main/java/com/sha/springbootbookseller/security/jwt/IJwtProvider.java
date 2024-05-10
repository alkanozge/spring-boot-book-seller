package com.sha.springbootbookseller.security.jwt;

import org.springframework.security.core.Authentication;
import com.sha.springbootbookseller.security.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

public interface IJwtProvider {
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
