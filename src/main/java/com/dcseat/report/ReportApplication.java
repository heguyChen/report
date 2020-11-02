package com.dcseat.report;

import com.dcseat.report.dao.seat.Users;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot 启动器
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dcseat.report"})
//@ComponentScan(basePackages = {"com.dcseat.report"})
public class ReportApplication implements CommandLineRunner {

    @Autowired
    private Users users;
    /**
     * SpringBoot 主程序入口
     */
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 接入自定义的main接口
        Integer a = users.getActiveUsersByCorp(98547771, "2020-10-1");
        System.out.println(a);
    }
}
