package blog.nen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //처음 컨트롤러 /로 가면 어디로 보낼지 정하는부분(?)
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //정적 리소스를 연결해주는 부분
        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/");
    }
}
