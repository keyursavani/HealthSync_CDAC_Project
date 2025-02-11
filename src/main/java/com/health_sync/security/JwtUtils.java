package com.health_sync.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${SECRET_KEY}")
	private String jwtSecret;

	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;

	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
		return Jwts.builder().setSubject((userPrincipal.getEmail())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				// setting a custom claim , to add granted authorities
				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				.signWith(key, SignatureAlgorithm.HS512).compact();
	}

	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream().map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		return authorityString;
	}

	public Authentication populateAuthenticationTokenFromJWT(String jwt) {
		// validate JWT n retrieve JWT body (claims)
		Claims payloadClaims = validateJwtToken(jwt);
		// get user name from the claims
		String email = getUserEmailFromJwtToken(payloadClaims);
		// get granted authorities as a custom claim
		List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
		// get userId as the custom claim
//		Long userId=getUserIdFromJwtToken(payloadClaims);
		// add user name/email , user id n granted authorities in Authentication object
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, "", authorities);
		return token;

	}

	public Claims validateJwtToken(String jwtToken) {

		Claims claims = Jwts.parserBuilder() // create JWT parser
				.setSigningKey(key) // sets the SAME secret key for JWT signature verification
				.build() // rets the JWT parser set with the Key
				.parseClaimsJws(jwtToken) // rets JWT with Claims added in the body
				.getBody();// => JWT valid , rets the Claims(payload)
		/*
		 * parseClaimsJws - throws:UnsupportedJwtException -if the JWT body | payload
		 * does not represent any Claims JWSMalformedJwtException - if the JWT body |
		 * payload is not a valid JWSSignatureException - if the JWT signature
		 * validation fails ExpiredJwtException - if the specified JWT is expired
		 * IllegalArgumentException - if the JWT claims body | payload is null or empty
		 * or only whitespace
		 */
		return claims;
	}

	public String getUserEmailFromJwtToken(Claims claims) {
		return claims.getSubject();
	}

	// this method will be invoked by our custom JWT filter to get list of granted
	// authorities n store it in auth token
	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		return authorities;
	}

//			public Long getUserIdFromJwtToken(Claims claims) {
//				return Long.valueOf((int)claims.get("user_id"));			
//			}

}
