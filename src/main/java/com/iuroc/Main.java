package com.iuroc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.iuroc.mapper.DeptMapper;
import com.iuroc.mapper.EmpMapper;

@SpringBootApplication
public class Main {
    @Autowired
    DeptMapper deptMapper;

    @Autowired
    EmpMapper empMapper;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Main main = context.getBean(Main.class, (Object[]) args);
        main.deptMapper.init();
        main.empMapper.init();
        System.out.println("http://127.0.0.1:9999");
    }
}