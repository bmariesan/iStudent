package ro.ubb.istudent.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SecurityTokenProperties {

    @Value("${security.token.secretKey}")
    private String secretKey;

    @Value("${security.token.header}")
    private String header;

    @Value("${security.token.expiration}")
    private Long expiration;

}
