package com.example.poiutis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
//public class Bootstrap {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Bootstrap.class, args);
//	}
//
//}
/**
 * SpringBoot的启动入口类
 * @author wsp
 * @date 2019-07-16
 */
@SpringBootApplication(exclude={
		//禁用默认JPA配置
		HibernateJpaAutoConfiguration.class,
		//禁用MongoDB的自动配置
		MongoAutoConfiguration.class,
		//禁用MongoDB连接的
		// 自动配置
		MongoDataAutoConfiguration.class})
@ComponentScan({"com.example.poiutis"})
@MapperScan({"com.example.poiutis.dao"})
@EnableTransactionManagement
@EnableScheduling
//@EnableSwagger2
public class Bootstrap{
	public static void main(String[] args) {
		//为Spring应用创建并初始化上下文
		SpringApplication.run(Bootstrap.class, args);
	}
}