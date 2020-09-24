package blog.nen.config;

import blog.nen.dao.BoardDao;
import blog.nen.dao.UserDao;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DBConfig {
    @Bean
    DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/nenblog");
        dataSource.setUsername("nenblog");
        dataSource.setPassword("123321");
        return dataSource;
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    public BoardDao boardDao() {
        return new BoardDao(dataSource());
    }
}

