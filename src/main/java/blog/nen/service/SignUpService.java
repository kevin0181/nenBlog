package blog.nen.service;

import blog.nen.Exception.Exception;
import blog.nen.Exception.SameEmailException;
import blog.nen.dao.SignUpDto;
import blog.nen.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpService {

    @Autowired
    private UserDao userDao;

    public void signUp(SignUpDto signUpDto) {
        SignUpDto sameCheck = userDao.selectUser(signUpDto);
        if (sameCheck != null) {
            throw new SameEmailException(); // 같은 이메일이 있으면 익셉션 처리
        }
        boolean signUpCheck = userDao.insert(signUpDto);
        if (!signUpCheck) {
            throw new Exception(); // 만약에 익셉션 뜨면 처리하는 부분
        }
    }
}
