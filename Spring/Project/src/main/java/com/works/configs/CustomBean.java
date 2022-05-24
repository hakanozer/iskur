package com.works.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class CustomBean {

    @Bean(name = "conn1")
    public Connection con() {
        System.out.println("conn1 call");
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return cn;
    }


    @Bean(name = "conn2")
    public Connection con1() {
        System.out.println("conn2 call");
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return cn;
    }

}
