package blog.nen.service;

import blog.nen.Exception.Exception;
import blog.nen.dao.BoardDao;
import blog.nen.dto.BoardDto;
import blog.nen.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    //카테고리 가져오는 서비스
    public List<BoardDto> getCategoryService(HttpSession session) {
        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        String sessionEmail = loginDto.getEmail();
        List<BoardDto> categoryList = boardDao.getCategory(sessionEmail);
        //카테고리가 존재하지 않을 때 익셉션
        if (categoryList == null)
            throw new Exception();

        return categoryList;
    }

    //글쓰기 서비스
    public void inputBoardService(BoardDto boardDto, HttpSession session) {
        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        boolean results = boardDao.inputBoardDao(boardDto, loginDto.getEmail());
        if (!results)
            throw new Exception();
    }

    //글 목록 서비스
    public List<BoardDto> getBoardService() {
        List<BoardDto> results = boardDao.getBoard();
        return results;
    }

    //해당 아이디 글 가져오는 서비스
    public List<BoardDto> getBoardIdService(String id) {
        List<BoardDto> results = boardDao.getBoardId(id);
        return results;
    }

    public void deleteBoardService(String id, HttpSession session) {
        if (id == null)
            throw new Exception();
        LoginDto loginDto = (LoginDto) session.getAttribute("userLogin");
        if (loginDto.getEmail() == null)
            throw new Exception();

        boardDao.deleteBoard(id, loginDto.getEmail());

    }
}