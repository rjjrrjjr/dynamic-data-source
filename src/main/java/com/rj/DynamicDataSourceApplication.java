package com.rj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.rj.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DynamicDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDataSourceApplication.class, args);
	}

}
