package com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.configuration;

import com.GestionDesNotesDeveloppeur.GestionDesNotesDeveloppeur.repositories.UtilisateurRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Configuration
public class SecurityConfiguration {
    @Autowired
    private UtilisateurRepositorie utilisateurRepositorie;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    public SecurityConfiguration() {
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return (UserDetails)SecurityConfiguration.this.utilisateurRepositorie.findByEmail(email).orElseThrow(() -> {
                    return new UsernameNotFoundException("User " + email + " not found");
                });
            }
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[]{"/auth/login","/auth/changePassword","/auth/user-details","/user/register","/user/list","/categorie/new/{idUser}","/categorie/categorie/{id}","/categorie/categories/{id}","/categorie/deleteCategorie/{id}","/categorie/update/{id}","/categorie/rechercher","/note/notes/{id}","/note/ajouterNote/{id}","/note/supprimerNote/{id}","/note/modifierNote/{id}","/note/getNote/{id}","/note/rechercher"})).permitAll().anyRequest()).authenticated();
        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(401, ex.getMessage());
        });
        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        });
        http.addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }
    @Bean
   public CorsConfigurationSource corsFilter(){
       CorsConfiguration corsConfiguration=new CorsConfiguration();
       corsConfiguration.addAllowedOrigin("*");
       corsConfiguration.addAllowedMethod("*");
       corsConfiguration.addAllowedHeader("*");
       UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
       urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
       return urlBasedCorsConfigurationSource;
   }
    /*@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[]{"/auth/login", "/user/register","/user/list"})).permitAll().anyRequest()).authenticated();

        http.addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }*/

    }



