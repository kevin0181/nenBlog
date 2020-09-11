package blog.nen.config;

import blog.nen.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public SignUpService signUpService() {
        return new SignUpService();
    }
}
