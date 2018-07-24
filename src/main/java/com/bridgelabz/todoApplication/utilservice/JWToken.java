package com.bridgelabz.todoApplication.utilservice;
/************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose: JW token implementation class for the login and registration
 *************************************************************************************************/
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWToken {

	public String createJWT(String issuer, String email) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date((System.currentTimeMillis()+600000)))
				.setIssuedAt(new Date())
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, "passKey");
		System.out.println(builder.compact());
		return builder.compact();
	}
	public String verifyToken(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey("passKey")
					.parseClaimsJws(token)
					.getBody();
			return claims.getSubject();
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			throw new JWTException("Error in verifying JW Token");
		} catch (ExpiredJwtException e) {
			throw new JWTException("Token Expired");
		}
	}
}
