package OrangeCompass.OrangeCompass.config;

import OrangeCompass.OrangeCompass.config.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling((exception) ->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler))

                .cors((configurationSource) -> this.corsConfigurationSource())

                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .requestMatchers(
                                        "/api/**/auth/sign-in",
                                        "/api/**/auth/sign-up",
                                        "/api/**/auth/reissue",
                                        "/swagger-ui").permitAll()

//                                .requestMatchers("/api/**/admin/**").hasAuthority("ROLE_USER")
//                                .requestMatchers("/api/**/members/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .anyRequest().authenticated()
                )

                // enable h2-console
                .headers(headers -> headers
                        .frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .logout(lout -> lout.disable())
                .sessionManagement(sessMng -> sessMng.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
//                .authorizeRequests(authorize -> authorize
//
//                )
                .addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/h2-console/**"
                , "/favicon.ico"
                , "/error");
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}











