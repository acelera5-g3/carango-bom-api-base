package br.com.caelum.carangobom.config;

import br.com.caelum.carangobom.controllers.AutenticacaoViaTokenFilter;
import br.com.caelum.carangobom.services.AutenticacaoService;
import br.com.caelum.carangobom.services.TokenService;
import br.com.caelum.carangobom.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;
    private AutenticacaoService autenticacaoService;

    @Autowired
    SecurityConfiguration(
            TokenService tokenService,
            UsuarioRepository usuarioRepository,
            AutenticacaoService autenticacaoService
    ) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Configs de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/marcas").permitAll()
                .antMatchers( "/usuarios").permitAll()
                .antMatchers( "/usuarios/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    // Configs de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
