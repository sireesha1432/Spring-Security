package com.SecurityExample.Controller;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private static final String secret ="abcdefghijklmnopqrstuvwxyz1234567891011121314151617181";

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return  Jwts.builder().setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*3)).
				signWith(getKey(),SignatureAlgorithm.HS256).compact();
	}

	private Key getKey() {
		byte[] byte1 = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(byte1);
	}

	public String extractUserName(String token) {
		final Claims claims = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUserName(token);
		Claims claims = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		return username.equals(userDetails.getUsername())&& !isTokenExpired;
	}
	

}
