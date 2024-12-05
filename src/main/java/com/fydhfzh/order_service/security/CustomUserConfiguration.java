package com.fydhfzh.order_service.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class CustomUserConfiguration {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select email, password, active from users where email=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select users.email, roles.role from roles "
                        + "inner join users on users.id = roles.user_id "
                        + "where users.email=?");

        return jdbcUserDetailsManager;
    }
}
