package com.lin.microservice.cloud.edge.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Data;

@Component
@Data
public class JwtHelper {
	private static Logger logger = LoggerFactory.getLogger(JwtHelper.class);
	
    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
	
	public String generateToken(Authentication auth) throws UnsupportedEncodingException {
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(auth.getName())	
			// Convert to list of strings. 
			// This is important because it affects the way we get them back in the Gateway.
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + expiration * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, secret.getBytes("UTF-8"))
			.compact();
		
		logger.debug("User: "+auth.getName()+" Token: "+token);
		
		return token;
	}

	public Claims parseToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		return Jwts.parser()
				.setSigningKey(secret.getBytes("UTF-8"))
				.parseClaimsJws(token)
				.getBody();
		
	}
}
