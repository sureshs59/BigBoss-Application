package com.bigboss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		http.csrf(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults())
			.authorizeHttpRequests( 
				(auth) -> auth
					.requestMatchers("/api/v1/health/**").permitAll()
					.requestMatchers("/v3/api-docs/**").permitAll()
					.requestMatchers("/swagger-ui/**").permitAll()
					.requestMatchers("/swagger-ui.html").permitAll());
		
//		 http
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/public").permitAll()
//                 .requestMatchers("/admin").hasRole("ADMIN")
//                 .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
//                 .anyRequest().authenticated()
//         )
//         .httpBasic(Customizer.withDefaults());

		 return http.build();
	}
	
	@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails user = User.builder()
                .username("suresh")
                .password(encoder.encode("password123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
