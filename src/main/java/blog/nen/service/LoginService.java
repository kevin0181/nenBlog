package blog.nen.service;

import blog.nen.Exception.NotFoundException;
import blog.nen.dto.LoginDto;
import blog.nen.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class LoginService {

    @Autowired
    private UserDao userDao;

    public void loginService(LoginDto loginDto, HttpSession session) {
        List<LoginDto> results = userDao.login(loginDto.getEmail(), loginDto.getPassword());

        if (results.isEmpty())
            throw new NotFoundException();

        if (results.get(0).getEmail() == loginDto.getEmail() && results.get(0).getPassword() == loginDto.getPassword()) {
            session.setAttribute("loginIng", loginDto);
        }

    }

}
