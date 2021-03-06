package com.SoubraTeam.GConge.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.SoubraTeam.GConge.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static com.SoubraTeam.GConge.security.SecurityConstants.SECRETE;
import static com.SoubraTeam.GConge.security.SecurityConstants.EXPIRATIONTIME;

@Component
public class JwtTokenProvider {

	//Generate a token
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis()); 
		Date expiredDate = new Date(now.getTime() + EXPIRATIONTIME); 
		
		String userId = Long.toString(user.getId());
		
		Map<String,Object> claims = new HashMap<>();
		claims.put("id",(Long.toString(user.getId())) );
		claims.put("username", user.getUsername());
		claims.put("fullname", user.getFullName());
		claims.put("isAdmin", Boolean.toString(user.getIsAdmin()));
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiredDate)
				.signWith(SignatureAlgorithm.HS512, SECRETE)
				.compact();
				
	}
	
	//validate token
	
	//Get user id from token
	
}
