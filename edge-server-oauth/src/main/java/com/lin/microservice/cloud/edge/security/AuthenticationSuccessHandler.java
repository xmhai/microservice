package com.lin.microservice.cloud.edge.security;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    JwtHelper tokenHelper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ){
        clearAuthenticationAttributes(request);
        
		try {
			String token = tokenHelper.generateToken(authentication);

			// Add token to header
			response.addHeader(tokenHelper.getHeader(), tokenHelper.getPrefix() + token);			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
    }
}