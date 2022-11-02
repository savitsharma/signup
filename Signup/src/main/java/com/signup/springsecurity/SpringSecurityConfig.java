package com.signup.springsecurity;

import com.signup.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService customUserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//                 http.authorizeRequests()
//                 .antMatchers("/usercontroller/**")
//                         .hasRole("ADMIN")
//                .anyRequest().permitAll().anyRequest().authenticated()
//                .and().formLogin().permitAll();


//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/usercontroller/**")
//                .permitAll().antMatchers()
//                .hasAnyAuthority()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers("/usercontroller/**")
                .authenticated()
                .anyRequest()
                .hasAnyRole("ADMIN")
                .and()
                .formLogin().and().logout()
                .permitAll();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("akash").password("123").roles("NORMAL");
//        auth.inMemoryAuthentication().withUser("harsh").password("123").roles("ADMIN");

   }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

//   @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();

    }

}
