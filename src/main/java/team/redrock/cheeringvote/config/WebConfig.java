//package team.redrock.cheeringvote.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
///**
// * @author 陌花采撷
// */
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer{
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix(null);
//        viewResolver.setSuffix(null);
//
//        registry.viewResolver(viewResolver);
//        registry.order(1);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/pic/");
//    }
//}
