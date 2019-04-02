package team.redrock.cheeringvote;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import team.redrock.cheeringvote.config.SpringContextHolder;

/**
 * @author 陌花采撷
 */
@EnableScheduling
@MapperScan("team.redrock.cheeringvote.mapper")
@SpringBootApplication
public class CheeringVoteApplication  extends SpringBootServletInitializer {


    public static void main(String[] args) {

       ApplicationContext applicationContext =  SpringApplication.run(CheeringVoteApplication.class, args);
        SpringContextHolder.setContext(applicationContext);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }

}
