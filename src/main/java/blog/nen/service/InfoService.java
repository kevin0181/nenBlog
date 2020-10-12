package blog.nen.service;

import blog.nen.Exception.Exception;
import blog.nen.Exception.WrongException;
import blog.nen.dao.BoardDao;
import blog.nen.dao.UserDao;
import blog.nen.dto.BoardDto;
import blog.nen.dto.ChangeInfoDto;
import blog.nen.dto.LoginDto;
import blog.nen.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class InfoService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BoardDao boardDao;

    public SignUpDto selectUserService(HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto.getEmail();

        if (email == null)
            throw new Exception();

        SignUpDto results = userDao.selectUser(email);

        if (results == null)
            throw new Exception();

        return results;
    }

    public List<BoardDto> selectCategoryService(HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto.getEmail();

        if (email == null)
            throw new Exception();

        List<BoardDto> results = boardDao.getCategory(email);

        if (results.isEmpty())
            throw new Exception();

        return results;
    }

    public void insertCategoryService(BoardDto BoardDto, HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto.getEmail();

        if (email == null)
            throw new Exception();

        boardDao.insertCategory(BoardDto, email);

    }

    public void deleteCategoryService(String id, HttpSession session) {
        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto.getEmail();

        if (email == null)
            throw new Exception();

        boardDao.deleteCategory(id, email);

    }

    public void updateUserService(ChangeInfoDto changeInfoDto, HttpSession session) {
        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto.getEmail();

        if (email == null)
            throw new Exception();

        userDao.UpdateUser(changeInfoDto, email);
    }

    public List<LoginDto> userDeleteService(LoginDto loginDto, HttpSession session) {
        LoginDto loginDto1 = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto1.getEmail();

        if (email == null)
            throw new Exception();

        List<LoginDto> results = userDao.login(email, loginDto.getPassword());

        if (results.isEmpty()) //삭제 시 비밀번호가 틀렸을 경우
            throw new WrongException();

        userDao.deleteUser(email, loginDto.getPassword());

        session.invalidate();

        return null;
    }
}
