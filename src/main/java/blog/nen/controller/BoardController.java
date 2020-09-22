package blog.nen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("input_board")
    public String inputBord() {
        //글쓰기 클릭시 페이지 이동
        return "board/input_board";
    }
}
