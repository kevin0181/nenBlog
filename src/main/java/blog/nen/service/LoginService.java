package blog.nen.service;

import blog.nen.Exception.NotFoundException;
import blog.nen.Exception.WrongException;
import blog.nen.dto.AuthDto;
import blog.nen.dto.LoginDto;
import blog.nen.dao.UserDao;
import blog.nen.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class LoginService {

    @Autowired
    private UserDao userDao;

    public void loginService(LoginDto loginDto, HttpSession session) {
        SignUpDto emailCheck = userDao.selectUser(loginDto.getEmail()); //이메일이 존재하는지 체크
        List<LoginDto> results = userDao.login(loginDto.getEmail(), loginDto.getPassword()); //로그인


        if (emailCheck == null) //이메일이 존재하지 않을때
            throw new NotFoundException();

        if (results.isEmpty()) //로그인 시 틀렸을 경우
            throw new WrongException();
        else
            session.setAttribute("userLogin", loginDto);

    }

    public void userCheckService(String email, HttpSession session) {
        AuthDto authDto = userDao.checkUser(email);

        if (authDto.isAuth())
            session.setAttribute("user_Auth", true);
        else
            session.setAttribute("user_Auth", false);

    }
}
