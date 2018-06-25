package com.inventory.web.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.inventory.common.service.impl.entitlement.InvUserDetailsServiceImpl;
import com.inventory.web.security.CustomAuthenticationFailureHandler;
import com.inventory.web.security.CustomDaoAuthenticationProvider;
import com.inventory.web.security.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	/*@Bean
	public CustomUserNamePasswordAuthenticationFilter customUserNamePasswordAuthenticationFilter() throws Exception {
		CustomUserNamePasswordAuthenticationFilter customUserNamePasswordAuthenticationFilter =  new CustomUserNamePasswordAuthenticationFilter();
		customUserNamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
		return customUserNamePasswordAuthenticationFilter;
	}*/

	@Autowired
	private UserDetailsChecker userDetailsChecker;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		CustomDaoAuthenticationProvider customDaoAuthenticationProvider =  new CustomDaoAuthenticationProvider();
		customDaoAuthenticationProvider.setUserDetailsService(userDetailsService);
		customDaoAuthenticationProvider.setPreAuthenticationChecks(userDetailsChecker);
		return customDaoAuthenticationProvider;
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new InvUserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomAuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider).userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		logger.debug("starting security configuration");
		//http.csrf().disable();
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.userDetailsService(userDetailsService());//.addFilter(customUserNamePasswordAuthenticationFilter());
		//authorize request
        http
        	.authorizeRequests()
         	.antMatchers("/", "/login", "/registration", "/service","/changePassword").permitAll()
                .anyRequest().authenticated()
                .and()
            //login configuration
            .formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .usernameParameter("email")
        		.passwordParameter("password").failureHandler(authenticationFailureHandler())
                .permitAll()
                .and()
             //logout configuration
            .logout()
            	.deleteCookies("remove")
            	.invalidateHttpSession(Boolean.TRUE)
            	.logoutUrl("/logout").clearAuthentication(Boolean.TRUE).logoutSuccessHandler(logoutSuccessHandler())
                .permitAll();
        
        http.sessionManagement().enableSessionUrlRewriting(Boolean.FALSE).maximumSessions(1).expiredUrl("/").maxSessionsPreventsLogin(Boolean.TRUE);
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/views/**","/css/**", "/js/**", "/images/**", "/externalservice/**", "/bootstrap/***", "/scripts/**");
	}
}
