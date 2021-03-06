package com.yp.ypproviderservice;

import com.yp.ypproviderdatasource.config.DBConfig1;
import com.yp.ypproviderdatasource.config.DBConfig2;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages="com.yp.ypproviderservice.service",
exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.yp.**")
@MapperScan(basePackages = "com.yp.ypprovidermapper")
@EnableCaching
@EnableConfigurationProperties(value = {DBConfig1.class, DBConfig2.class})
@EnableTransactionManagement
public class YpProviderServiceApplication implements CommandLineRunner, DisposableBean{

	private  final Logger log = LoggerFactory.getLogger(YpProviderServiceApplication.class);
	private final static CountDownLatch latch = new CountDownLatch(1);
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) throws Exception{
		context = SpringApplication.run(YpProviderServiceApplication.class, args);
		latch.await();
	}

	@Override
	public void destroy() throws Exception {
		latch.countDown();
		context.close();
		log.info("服务提供者关闭------>>服务关闭");
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("服务提供者启动完毕------>>启动完毕");
	}
}
