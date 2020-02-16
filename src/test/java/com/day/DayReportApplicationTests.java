package com.day;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class DayReportApplicationTests {

    @Autowired
    DataSource dataSource;


    void contextLoads() throws SQLException {

    }

}
