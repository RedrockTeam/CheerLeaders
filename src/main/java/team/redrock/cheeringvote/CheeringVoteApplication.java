package team.redrock.cheeringvote;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import team.redrock.cheeringvote.config.SpringContextHolder;

/**
 * @author 陌花采撷
 */
@EnableScheduling
@MapperScan("team.redrock.cheeringvote.mybatis")
@ComponentScan(basePackages = {"team.redrock"})
@SpringBootApplication
public class CheeringVoteApplication  extends SpringBootServletInitializer {


    public static void main(String[] args) {

       ApplicationContext applicationContext =  SpringApplication.run(CheeringVoteApplication.class, args);
        SpringContextHolder.setContext(applicationContext);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(CheeringVoteApplication.class);
    }

}
