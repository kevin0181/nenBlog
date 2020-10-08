package blog.nen.service;

import blog.nen.Exception.Exception;
import blog.nen.dao.BoardDao;
import blog.nen.dao.UserDao;
import blog.nen.dto.BoardDto;
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

    public void insertCategoryService(BoardDto boardDto, HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String email = loginDto.getEmail();

        if (email == null)
            throw new Exception();

        boardDao.insertCategory(boardDto, email);

    }
}
