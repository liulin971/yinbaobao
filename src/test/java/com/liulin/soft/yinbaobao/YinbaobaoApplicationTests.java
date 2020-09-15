package com.liulin.soft.yinbaobao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class YinbaobaoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("测试提交");
    }


    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads1() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

}
