package blog.nen.service;

import blog.nen.Exception.Exception;
import blog.nen.dao.BoardDao;
import blog.nen.dto.BoardCategory;
import blog.nen.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public List<BoardCategory> getCategoryService(HttpSession session) {
        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String sessionEmail = loginDto.getEmail();
        List<BoardCategory> categoryList = boardDao.getCategory(sessionEmail);
        //카테고리가 존재하지 않을 때 익셉션
        if (categoryList == null)
            throw new Exception();

        return categoryList;
    }
}
