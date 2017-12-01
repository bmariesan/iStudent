package ro.ubb.istudent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ro.ubb.istudent.security.AuthenticationTokenFilter;
import ro.ubb.istudent.security.EntryPointUnauthorizedHandler;

@EnableWebSecurity
@EnableScheduling
@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMongoAuditing
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserDetailsService userDetailsService;
    private final EntryPointUnauthorizedHandler unauthorizedHandler;

    @Autowired
    public WebSecurityConfiguration(CorsFilter corsFilter, UserDetailsService userDetailsService, EntryPointUnauthorizedHandler unauthorizedHandler) {
        this.corsFilter = corsFilter;
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    public WebSecurityConfiguration(boolean disableDefaults, CorsFilter corsFilter, UserDetailsService userDetailsService, EntryPointUnauthorizedHandler unauthorizedHandler) {
        super(disableDefaults);
        this.corsFilter = corsFilter;
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // note: you can add as well js, css, png etc files if needed
                // just follow the same pattern changing the file extension
                // for static file serving check also the application.properties file
                // you should indicate the path to resources -> static-location
                .regexMatchers("/").permitAll()
                .regexMatchers("/.*.html").permitAll()
                .regexMatchers("/.*.js").permitAll()
                .regexMatchers("/.*.css").permitAll()
                .regexMatchers("/.*.png").permitAll()
                .regexMatchers("/.*.jpg").permitAll()
                .regexMatchers("/.*.jpeg").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/save").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return authenticationTokenFilter;
    }

    @Configuration
    public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("forward: public/index.html");
            super.addViewControllers(registry);
        }
    }

}
