package blog.nen.dao;

import blog.nen.dto.LoginDto;
import blog.nen.dto.SignUpDto;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final String category = "기본";

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public SignUpDto selectUser(String email) {
        // 회원 가입 시 같은 이메일이 있는지 검사하는 부분. (전화번호도 추가하자)
        List<SignUpDto> results = jdbcTemplate.query("select * from user_info where EMAIL = ?",
                (ResultSet rs, int rowNum) -> {
                    SignUpDto signUpDto = new SignUpDto(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("PHONE")
                    );
                    return signUpDto;
                }, email);

        return results.isEmpty() ? null : results.get(0);

    }

    public boolean insert(SignUpDto signUpDto) {
        // 회원가입
        //USERAUTH 테이블에 데이터 입력 (권한 테이블)
        try {
            jdbcTemplate.update("insert into USER_AUTH(EMAIL,AUTH) values (?,?)", signUpDto.getEmail(), false);
        } catch (Exception e) {
            return false;
        }

        //USERINFO 테이블에 데이터 입력
        try {
            jdbcTemplate.update("insert into user_info (EMAIL,PASSWORD,PHONE) values (?,?,?)",
                    signUpDto.getEmail(), signUpDto.getPassword(), signUpDto.getPhone(), signUpDto.getEmail());
            jdbcTemplate.update("insert into USER_CATEGORY(EMAIL, CATEGORY) VALUES (?,?)", signUpDto.getEmail(), category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean update() {
        //수정
        return true;
    }

    public List<LoginDto> login(String email, String password) {
        //로그인
        List<LoginDto> results = jdbcTemplate.query("select EMAIL, PASSWORD from user_info where EMAIL = ? and PASSWORD = ?",
                (ResultSet rs, int rowNum) -> {
                    LoginDto loginDto = new LoginDto(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD")
                    );
                    return loginDto;
                }, email, password);
        return results;
    }
}

