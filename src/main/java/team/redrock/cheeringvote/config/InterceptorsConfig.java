package team.redrock.cheeringvote.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.redrock.cheeringvote.interceptor.LoginInterceptor;


/**
 * @author 陌花采撷
 */
@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有的controller
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").addPathPatterns("/cheering_vote").excludePathPatterns("/pic/**");
    }

}
