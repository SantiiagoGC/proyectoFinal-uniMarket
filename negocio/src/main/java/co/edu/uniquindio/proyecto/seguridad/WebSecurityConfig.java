package co.edu.uniquindio.proyecto.seguridad;

import co.edu.uniquindio.proyecto.seguridad.config.JwtAuthenticationEntryPoint;
import co.edu.uniquindio.proyecto.seguridad.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers( "/doc/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
        http.csrf().disable();
        http.cors();
        http.authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll();
        http.authorizeHttpRequests().requestMatchers(
                "/api/productos/obtener/**",
                "/api/productos/crear",
                "/api/productos/obtener_productos",
                "/api/productos/obtener_productos_categoria/**",
                "/api/productos/obtener_productos_precio/**",
                "/api/productos/obtener_productos_titulo/**",
                "/api/categorias/obtener",
                "/api/productos/compra/**",
                "/api/clientes/**"
                ,"/api/categorias/obtener/**"
        ).permitAll().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}


