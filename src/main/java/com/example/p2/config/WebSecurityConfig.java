package com.example.p2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity @Slf4j @SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] STATIC_RESOURCES = { "/css/**", "/images/**", "/favicon/**","/webjars/**" };
    public static final String ZUL_FILES = "/zkau/web/**/*.zul";
    public static final String[] ZK_RESOURCES = {"/zkau/web/**/js/**", "/zkau/web/**/zul/css/**", "/zkau/web/**/img/**"};
    // allow desktop cleanup after logout or when reloading login page
    public static final String REMOVE_DESKTOP_REGEX = "/zkau\\?dtid=.*&cmd_0=rmDesktop&.*";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(ZUL_FILES).denyAll() // block direct access to zul files
                    .antMatchers(HttpMethod.GET, ZK_RESOURCES).permitAll() // allow zk resources
                    .antMatchers(HttpMethod.GET, STATIC_RESOURCES).permitAll() // allow static resources
                    .regexMatchers(HttpMethod.GET, REMOVE_DESKTOP_REGEX).permitAll() // allow desktop cleanup
                    .requestMatchers(req -> "rmDesktop".equals(req.getParameter("cmd_0"))).permitAll() // allow desktop cleanup from ZATS
                    .mvcMatchers("/", "/login", "/logout").permitAll()
                    .mvcMatchers("/user/**").hasRole("USER")
                    .mvcMatchers("/admin/**", "/h2-console").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin().and()
//              .loginPage("/login").defaultSuccessUrl("/secure/main").and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").and()
                .httpBasic()
                    .realmName("MainRealm")
                .and()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("user")
                    .password("{noop}password")
                    .roles("USER")
                .and()
                    .withUser("admin")
                    .password("{noop}password2")
                    .roles("ADMIN","USER")
        ;
    }
}
