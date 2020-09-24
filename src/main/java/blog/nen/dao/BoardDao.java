package blog.nen.dao;

import blog.nen.dto.BoardCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Configuration
public class BoardDao {
    private final JdbcTemplate jdbcTemplate;

    public BoardDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<BoardCategory> getCategory(String sessionEmail) {
        //카테고리 가져오는 부분
        List<BoardCategory> results = jdbcTemplate.query("select CATEGORY from user_category where EMAIL = ?",
                (ResultSet rs, int rowNum) -> {
                    BoardCategory boardCategory = new BoardCategory(
                            rs.getString("CATEGORY")
                    );
                    return boardCategory;
                }, sessionEmail);
        return results.isEmpty() ? null : results;
    }
}
