package br.com.lucaslememoura.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static SecretKey secretKeyRefresh = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generateRefreshToken(String username) {
		return generateTokenBase(username,secretKeyRefresh,60l);
	}

	public String generateToken(String username) {
		return generateTokenBase(username,secretKey,30l);
	}
	
	private String generateTokenBase(String username, SecretKey key, Long minutosExpiracao) {
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime dataExpiracao = dataAtual.plusMinutes(minutosExpiracao);
		
		return Jwts.builder()
			.setIssuedAt(new Date(dataAtual.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
			.setExpiration(new Date(dataExpiracao.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
			.setSubject(username)
			.signWith(key)
			.compact();
	}
	
	public Boolean validToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.isSigned(token);
	}
	
	public Boolean validRefreshToken(String refreshToken) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKeyRefresh)
			.build()
			.isSigned(refreshToken);
	}
	
	public String getUsernameByToken(String token) {
		return getUsernameByTokenBase(token,secretKey);
	}
	
	public String getUsernameByRefreshToken(String refreshToken) {
		return getUsernameByTokenBase(refreshToken,secretKeyRefresh);
	}
	
	private String getUsernameByTokenBase(String token, SecretKey key) {
		return Jwts.parserBuilder()
					.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
