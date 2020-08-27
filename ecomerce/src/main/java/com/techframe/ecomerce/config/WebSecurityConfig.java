package com.techframe.ecomerce.config;

import javax.sql.DataSource;

import com.techframe.ecomerce.model.Users;
import com.techframe.ecomerce.repository.UsersRepository;
import com.techframe.ecomerce.service.UsersServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    @Autowired
    public UsersServiceM usersServiceM;
    public UsersRepository usersRepository;

    @Autowired
    private void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?")

        ;
    }
//namhm codejava user
//admin     nimda
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests().
                antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
