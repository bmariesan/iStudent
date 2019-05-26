package ro.ubb.istudent.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.config.SecurityTokenProperties;
import ro.ubb.istudent.dto.SecurityUser;
import ro.ubb.istudent.repository.ValidTokenRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ro.ubb.istudent.util.StringUtils.CREATED;
import static ro.ubb.istudent.util.StringUtils.SUB;

@Component
public class TokenUtils {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final SecurityTokenProperties tokenProperties;
    private ValidTokenRepository validTokenRepository;

    @Autowired
    public TokenUtils(SecurityTokenProperties tokenProperties, ValidTokenRepository validTokenRepository) {
        this.tokenProperties = tokenProperties;
        this.validTokenRepository = validTokenRepository;
    }

    /**
     * Generate a new token for the given userDetails
     *
     * @param userDetails the user details to generate the token for
     * @return the token generated
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUB, userDetails.getUsername());
        claims.put(CREATED, this.generateCurrentDate());
        return this.generateToken(claims);
    }

    /**
     * Verify if the given token is a valid token of the user details given
     *
     * @param token       the token to be verified
     * @param userDetails the user details
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        SecurityUser user = (SecurityUser) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        boolean tokenIsValid = validTokenRepository.findByTokenAndExpirationDateAfter(
                token, new Date()) != null;

        return (username.equals(user.getUsername())
                && !(this.isTokenExpired(token)) && tokenIsValid);
    }


    /**
     * Read the username form the given token
     *
     * @param token the token to read the username from
     * @return the username read
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Read created date property from a given token
     *
     * @param token to read from
     * @return the created date
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            logger.error(e.toString());
            created = null;
        }
        return created;
    }

    /**
     * Read expiration date property from a given token
     *
     * @param token to read from
     * @return the expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            logger.error(e.toString());
            expiration = null;
        }
        return expiration;
    }

    /**
     * Read the claims from a token
     *
     * @param token to read form
     * @return the Claims object read
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error(e.toString());
            claims = null;
        }
        return claims;
    }

    /**
     * Generate the current date
     *
     * @return the date generated
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Generate the expiration date relative to the current date
     *
     * @return the date generated
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + tokenProperties.getExpiration() * 1000);
    }

    /**
     * Verify if the token given is expired
     *
     * @param token the token to be verified
     * @return true if the token is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }


    /**
     * Verify if the creation date is before the given lastPasswordReset date
     *
     * @param created           the token creation date
     * @param lastPasswordReset the last date of password reset for a user
     * @return true if the token is created before the last password reset, false otherwise
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * Generate a new token for the claims given
     *
     * @param claims the claims to generate the token for
     * @return the token generated
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecretKey())
                .compact();
    }

}
