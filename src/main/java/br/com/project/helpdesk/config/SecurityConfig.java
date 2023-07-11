package br.com.project.helpdesk.config;

import br.com.project.helpdesk.security.JWTAuthenticationFilter;
import br.com.project.helpdesk.security.JWTAuthorizationFilter;
import br.com.project.helpdesk.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = { "/login/**" };

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authConfiguration) throws Exception {
        //http.authorizeHttpRequests().anyRequest().permitAll(); //Libera acesso a todos os metodos
        //http.httpBasic();
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //desliga a protecao a csrf, o que permite executar metodos POST,PUT,DELETE sem sessao de usuario
        http.authorizeHttpRequests().requestMatchers(PathRequest.toH2Console()).permitAll().and().headers().frameOptions().disable(); //libera acesso ao h2-console
        http.authorizeHttpRequests().requestMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
        //http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/tecnicos/**").permitAll().anyRequest().authenticated();
        http.cors();

        http.addFilter(new JWTAuthenticationFilter(authConfiguration.getAuthenticationManager(), jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authConfiguration.getAuthenticationManager(), jwtUtil, userDetailsService));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
