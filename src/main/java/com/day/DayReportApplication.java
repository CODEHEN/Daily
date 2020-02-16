package com.day;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
public class DayReportApplication {



    public static void main(String[] args) {
        SpringApplication.run(DayReportApplication.class, args);
    }

}
