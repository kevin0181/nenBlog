package blog.nen.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public SignUpDto selectUser(SignUpDto signUpDto) {
        // 회원 가입 시 같은 이메일이 있는지 검사하는 부분. (전화번호도 추가하자)
        List<SignUpDto> results = jdbcTemplate.query("select * from userinfo where EMAIL = ?",
                (ResultSet rs, int rowNum) -> {
                    SignUpDto signUpDto1 = new SignUpDto(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("PHONE")
                    );
                    return signUpDto1;
                }, signUpDto.getEmail());

        return results.isEmpty() ? null : results.get(0);

    }

    public boolean insert(SignUpDto signUpDto) {
        // 회원가입
        try {
            jdbcTemplate.update("insert into userinfo (EMAIL,PASSWORD,PHONE) values (?,?,?)",
                    signUpDto.getEmail(), signUpDto.getPassword(), signUpDto.getPhone());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update() {
        //수정
        return true;
    }

    public LoginDto login() {
        //로그인
        return null;
    }
}
