package blog.nen.config;

import blog.nen.service.LoginService;
import blog.nen.service.SignUpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import blog.nen.service.InfoService;

@Configuration
public class UserConfig {

    @Bean
    public SignUpService signUpService() {
        return new SignUpService();
    }

    @Bean
    public LoginService loginService() {
        return new LoginService();
    }

    @Bean
    public InfoService infoService() {
        return new InfoService();
    }

}
