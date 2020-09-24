package blog.nen.controller;

import blog.nen.Exception.Exception;
import blog.nen.config.BoardConfig;
import blog.nen.config.DBConfig;
import blog.nen.dto.BoardCategory;
import blog.nen.dto.BoardDto;
import blog.nen.service.BoardService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BoardConfig.class, DBConfig.class);

    @GetMapping("input_board")
    public String inputBord(@ModelAttribute("boardCategory") BoardCategory boardCategory,
                            HttpSession session, Model model) {
        //글쓰기 클릭시 페이지 이동
        //글쓴이가 가지고 있는 카테고리 목록을 가져옴
        try {
            BoardService boardService = ctx.getBean(BoardService.class);
            List<BoardCategory> categoryList = boardService.getCategoryService(session);
            model.addAttribute("categoryList", categoryList);
        } catch (Exception e) {

        }
        return "board/input_board";
    }
}
