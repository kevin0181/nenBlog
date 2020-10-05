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

    //카테고리 가져오는 부분
    public List<BoardDto> getCategory(String sessionEmail) {
        List<BoardDto> results = jdbcTemplate.query("select CATEGORY from user_category where EMAIL = ?",
                (ResultSet rs, int rowNum) -> {
                    BoardDto boardDto = new BoardDto(
                            rs.getString("CATEGORY")
                    );
                    return boardDto;
                }, sessionEmail);
        return results.isEmpty() ? null : results;
    }

    //글쓰기 dao
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

    //글 가져오는 dao (글목록)
    public List<BoardDto> getBoard() {
        List<BoardDto> results = jdbcTemplate.query("select * from user_board order by BOARD_ID",
                (ResultSet rs, int rowNum) -> {
                    BoardDto boardDto = new BoardDto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getBoolean(6),
                            rs.getString(7),
                            rs.getBoolean(8)
                    );
                    return boardDto;
                });
        return results;
    }

    //해당 아이디의 글 가져오는 dao
    public List<BoardDto> getBoardId(String id) {
        List<BoardDto> results = jdbcTemplate.query("select * from user_board where BOARD_ID = ?",
                (ResultSet rs, int rowNum) -> {
                    BoardDto boardDto = new BoardDto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getBoolean(6),
                            rs.getString(7),
                            rs.getBoolean(8)
                    );
                    return boardDto;
                }, id
        );
        return results;
    }

    public void deleteBoard(String id, String email) {
        jdbcTemplate.update("delete from user_board where BOARD_EMAIL = ? and BOARD_ID = ?", email, id);
    }
}
