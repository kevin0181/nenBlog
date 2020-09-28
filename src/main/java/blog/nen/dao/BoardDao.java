package blog.nen.dao;

import blog.nen.dto.BoardDto;
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

    public List<BoardDto> getCategory(String sessionEmail) {
        //카테고리 가져오는 부분
        List<BoardDto> results = jdbcTemplate.query("select CATEGORY from user_category where EMAIL = ?",
                (ResultSet rs, int rowNum) -> {
                    BoardDto boardDto = new BoardDto(
                            rs.getString("CATEGORY")
                    );
                    return boardDto;
                }, sessionEmail);
        return results.isEmpty() ? null : results;
    }

    public boolean inputBoardDao(BoardDto boardDto, String email) {
        try {
            jdbcTemplate.update("insert into user_board (BOARD_EMAIL, BOARD_DATE, BOARD_TITLE, BOARD_CATEGORY, BOARD_PUBLIC, BOARD_TEXT, BOARD_SAVE)" +
                            "values (?,now(),?,?,?,?,?)", email, boardDto.getBoardTitle(), boardDto.getBoardCategory(), boardDto.isBoardPublic(), boardDto.getBoardText()
                    , boardDto.isBoardSave());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
