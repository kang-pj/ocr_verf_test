package com.refine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.headers().frameOptions().sameOrigin();

		http.httpBasic().and().requiresChannel().and().authorizeRequests();

		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/main")
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler())
				.usernameParameter("inputId")
				.passwordParameter("inputPw")
				.permitAll();

		http.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler());

		http.logout()
			.logoutUrl("/logout") // default
			.logoutSuccessHandler(logoutSuccessHandler())
			.permitAll();
	}


	private AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/static/**","/resources/**", "/popResetPw", "/getResetPwAuthCode", "/resetPw", "/emailVerify", "/getEmailVerify");
		web.debug(false);
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler(){
	    return new CustomAuthenticationSuccessHandler();
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new CustomLogOutSuccessHandler();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
	  RoleHierarchyImpl r = new RoleHierarchyImpl();
	  r.setHierarchy(
			  "ROLE_ADMIN > ROLE_L_REMP > ROLE_L_ASST > ROLE_L_USER"
			+   System.lineSeparator() + "ROLE_ADMIN > ROLE_M_REMP > ROLE_M_ASST > ROLE_M_USER"
			+   System.lineSeparator() + "ROLE_ADMIN > ROLE_CS_REMP > ROLE_CS_ASST > ROLE_CS_ASTS > ROLE_CS_USER"
			);
	  return r;
	}

	private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
	    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
	    defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
	    return defaultWebSecurityExpressionHandler;
	}

    @Override
    public void init(WebSecurity web) throws Exception {
        web.expressionHandler(webExpressionHandler());
        super.init(web);
    }

/*
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
	    expressionHandler.setRoleHierarchy(roleHierarchy());
	    return expressionHandler;
	}
	*/
}
