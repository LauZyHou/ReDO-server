package com.ecnu.refactoring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ecnu.refactoring.*"})
@MapperScan("com.ecnu.refactoring.mapper")
@EnableSwagger2
//@ComponentScan(basePackages = {"com.ecnu.refactoring.config"})
public class RefactoringApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RefactoringApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RefactoringApplication.class);
    }
}