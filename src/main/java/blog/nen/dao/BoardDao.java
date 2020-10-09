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
        List<BoardDto> results = jdbcTemplate.query("select category_id, CATEGORY, CATEGORY_EX from user_category where EMAIL = ?",
                (ResultSet rs, int rowNum) -> {
                    BoardDto boardDto = new BoardDto(
                            rs.getInt("category_id"),
                            rs.getString("CATEGORY"),
                            rs.getString("CATEGORY_EX")
                    );
                    return boardDto;
                }, sessionEmail);
        return results.isEmpty() ? null : results;
    }

    public void insertCategory(BoardDto boardDto, String email) {
        jdbcTemplate.update("insert into user_category(email, category,CATEGORY_EX) values (?,?,?)",
                email, boardDto.getBoardCategory(), boardDto.getBoardCategory_Ex());
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
    public List<BoardDto> getBoardList() {
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

    //게시물 삭제하는 dao
    public void deleteBoard(String id, String email) {
        jdbcTemplate.update("delete from user_board where BOARD_EMAIL = ? and BOARD_ID = ?", email, id);
    }

    //게시물 가져오는 dao
    public BoardDto getBoard(String id, String email) {
        BoardDto boardDto =
                jdbcTemplate.queryForObject("select * from user_board where BOARD_EMAIL = ? and BOARD_ID = ?",
                        (ResultSet rs, int rowNum) -> {
                            BoardDto boardDto1 = new BoardDto(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDate(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    rs.getBoolean(6),
                                    rs.getString(7),
                                    rs.getBoolean(8)
                            );
                            return boardDto1;
                        }, email, id);
        return boardDto;
    }

    //게시물 수정하는 dao
    public void boardUpdate(BoardDto boardDto, String email) {
        jdbcTemplate.update("update user_board set BOARD_TITLE = ?, BOARD_CATEGORY = ?, BOARD_PUBLIC = ?, BOARD_TEXT = ?, BOARD_SAVE = ? " +
                        "where BOARD_ID = ? and BOARD_EMAIL = ?", boardDto.getBoardTitle(), boardDto.getBoardCategory(), boardDto.isBoardPublic(), boardDto.getBoardText(),
                boardDto.isBoardSave(), boardDto.getBoardId(), email);
    }

    public void deleteCategory(String id, String email) {
        jdbcTemplate.update("delete from user_category where category_id = ? and EMAIL = ?", id, email);
    }
}
