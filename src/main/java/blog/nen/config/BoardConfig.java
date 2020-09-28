package blog.nen.config;

import blog.nen.service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardConfig {

    @Bean
    public BoardService BoardService() {
        return new BoardService();
    }
}
