package blog.nen.controller;

import blog.nen.Exception.Exception;
import blog.nen.config.BoardConfig;
import blog.nen.config.DBConfig;
import blog.nen.dto.BoardDto;
import blog.nen.service.BoardService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BoardController {

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BoardConfig.class, DBConfig.class);
    private BoardService boardService = ctx.getBean(BoardService.class);

    @RequestMapping("input_board")
    public String inputBord(@ModelAttribute("boardDto") BoardDto boardDto, HttpSession session, Model model) {
        //글쓰기 클릭시 페이지 이동
        //글쓴이가 가지고 있는 카테고리 목록을 가져옴
        try {
            List<BoardDto> categoryList = boardService.getCategoryService(session);
            model.addAttribute("categoryList", categoryList);
        } catch (Exception e) {
            return "Exception/Exception";
        }
        return "board/input_board";
    }

    @RequestMapping("input_board_main")
    public String inputBoardText(@Valid BoardDto boardDto, BindingResult bindingResult, HttpSession session, Model model) {
        //글쓰기 시 디비에 입력되는 부분
        try {
            if (bindingResult.hasErrors()) {
                List<BoardDto> categoryList = boardService.getCategoryService(session);
                model.addAttribute("categoryList", categoryList);
                return "board/input_board";
            }
            boardService.inputBoardService(boardDto, session);
        } catch (Exception e) {
            return "error/Exception";
        }

        List<BoardDto> results = boardService.getBoardService();
        model.addAttribute("BoardList", results);

        return "main";
    }

    @RequestMapping("boardId")
    public String boardId(@RequestParam("id") String id, Model model) {
        //게시물의 id값, 리스트를 가져오는 부분
        List<BoardDto> boardDto = boardService.getBoardIdService(id);
        model.addAttribute("boardDto", boardDto);
        return "board/board";
    }

    @RequestMapping("boardDelete")
    public String boardDelete(@RequestParam("id") String id, HttpSession session, Model model) {
        //게시물 삭제
        try {
            boardService.deleteBoardService(id, session);

            List<BoardDto> results = boardService.getBoardService(); //리스트 다시 가져옴
            model.addAttribute("BoardList", results);
        } catch (Exception e) {
            return "error/Exception";
        }
        return "main";
    }

    @RequestMapping("boardIdRe")
    public String boardRevise(@RequestParam("id") String id, HttpSession session, Model model) {
        try {
            BoardDto boardDto = boardService.reviseBoardService(id, session);
            model.addAttribute("boardDto", boardDto);

            List<BoardDto> categoryList = boardService.getCategoryService(session); //카테고리 가져옴
            model.addAttribute("categoryList", categoryList);
        } catch (Exception e) {
            return "error/Exception";
        }
        return "board/revise_input_board";
    }

    @RequestMapping("revise_board")
    public String revise_board_main(@Valid BoardDto boardDto, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors())
            return "board/revise_input_board";

        boardService.boardUpdateService(boardDto, session);

        List<BoardDto> results = boardService.getBoardService(); //리스트 다시 가져옴
        model.addAttribute("BoardList", results);

        return "main";
    }
}
