package ro.ubb.istudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.aspects.Loggable;
import ro.ubb.istudent.domain.ValidToken;
import ro.ubb.istudent.dto.AuthenticationRequest;
import ro.ubb.istudent.dto.AuthenticationResponse;
import ro.ubb.istudent.repository.ValidTokenRepository;
import ro.ubb.istudent.security.TokenUtils;
import ro.ubb.istudent.util.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;
    private final ValidTokenRepository validTokenRepository;

    @Autowired
    public AuthenticationController(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, ValidTokenRepository validTokenRepository) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.validTokenRepository = validTokenRepository;
    }

    @Loggable
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        // Perform the authentication
        String userNameOrEmail = authenticationRequest.getEmail() != null ? authenticationRequest.getEmail() : authenticationRequest.getUserName();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userNameOrEmail,
                        authenticationRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(userNameOrEmail);
        String token = tokenUtils.generateToken(userDetails);
        Date expirationDate = tokenUtils.getExpirationDateFromToken(token);

        validTokenRepository.save(ValidToken.builder()
                .token(token)
                .expirationDate(expirationDate)
                .build());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @RequestMapping(path = "/logoutMe", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> logout(HttpServletRequest httpRequest, HttpServletResponse response) {
        String authToken = httpRequest.getHeader(HttpHeaders.AUTH_TOKEN);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        if (tokenUtils.validateToken(authToken, userDetails)) {
            new SecurityContextLogoutHandler().logout(httpRequest, response, authentication);
            validTokenRepository.deleteByToken(authToken);
            return ResponseEntity.ok("done");
        } else {
            return ResponseEntity.ok("bad");
        }

    }

}
