package blog.nen.controller;

import blog.nen.Exception.Exception;
import blog.nen.Exception.NotFoundException;
import blog.nen.Exception.SameEmailException;
import blog.nen.Exception.WrongException;
import blog.nen.config.BoardConfig;
import blog.nen.config.DBConfig;
import blog.nen.config.UserConfig;
import blog.nen.dto.BoardDto;
import blog.nen.dto.LoginDto;
import blog.nen.dto.SignUpDto;
import blog.nen.service.BoardService;
import blog.nen.service.InfoService;
import blog.nen.service.LoginService;
import blog.nen.service.SignUpService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    private String email;

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfig.class, BoardConfig.class, DBConfig.class);

    @RequestMapping("/")
    public String startPage(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "index";
    }

    @RequestMapping("index")
    public String index(HttpSession session, Model model) {
        //redirect 시 '/'가 없으면 맵핑값을 기준으로 redirect 됨
        if (session.getAttribute("userLogin") == null)
            return "redirect:";

        BoardService boardService = ctx.getBean(BoardService.class);
        List<BoardDto> results = boardService.getBoardService();
        model.addAttribute("BoardList", results);

        return "main";
    }

    @RequestMapping("main")
    public String login(@Valid LoginDto loginDto, BindingResult bindingResult, HttpSession session, Model model) {
        //로그인 시 메인페이지로

        if (bindingResult.hasErrors()) {
            return "index";
        }

        try {

            LoginService loginService = ctx.getBean(LoginService.class);
            loginService.userCheckService(loginDto.getEmail(), session); //관리자인지 체크하는 부분
            checkUser(loginDto.getEmail());
            loginService.loginService(loginDto, session);

            BoardService boardService = ctx.getBean(BoardService.class);
            List<BoardDto> results = boardService.getBoardService();
            model.addAttribute("BoardList", results);

        } catch (NotFoundException e) {
            bindingResult.rejectValue("ErrorCode", "notFound.Error");
            return "index";
        } catch (WrongException e) {
            bindingResult.rejectValue("ErrorCode", "Wrong.Error");
            return "index";
        }
        return "main";
    }

    @GetMapping("main")
    public String mainGet(HttpSession session, Model model) {

        BoardService boardService = ctx.getBean(BoardService.class);
        List<BoardDto> results = boardService.getBoardService();
        model.addAttribute("BoardList", results);

        return "main";
    }

    @RequestMapping("logout")
    public String logout(@ModelAttribute("loginDto") LoginDto loginDto, HttpSession session) {
        //로그아웃
        session.invalidate();
        return "index";
    }


    @RequestMapping("signPage")
    public String signPage(@ModelAttribute("signUpDto") SignUpDto signUpDto) {
        //회원 가입 페이지로 이동
        return "signPage";
    }

    @RequestMapping("signUp")
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model) {
        //회원 가입 후 index 페이지로 리다이렉트

        if (bindingResult.hasErrors()) {
            return "signPage";
        }

        SignUpService signUpService = ctx.getBean(SignUpService.class);
        try {
            signUpService.signUpService(signUpDto);

        } catch (SameEmailException e) {
            bindingResult.rejectValue("email", "sameEmail.Error");
            return "signPage";
        } catch (Exception e) {
            model.addAttribute("ExceptionName", "Exception");
            return "error/Exception";
        }
        return "redirect:index";
    }

    @GetMapping("signUp")
    public String signUpGetException(Model model) {
        // signUp 주소창으로 입력했을때 익셉션
        model.addAttribute("ExceptionName", "singUpGetException");
        return "error/Exception";
    }

    @RequestMapping("info")
    public String info(@ModelAttribute("boardDto") BoardDto boardDto, Model model, HttpSession session) {

        try {
            InfoService infoService = ctx.getBean(InfoService.class);

            SignUpDto userInfo = infoService.selectUserService(session);
            model.addAttribute("userInfo", userInfo);

            List<BoardDto> userInfoCategory = infoService.selectCategoryService(session);
            model.addAttribute("userInfoCategory", userInfoCategory);
        } catch (Exception e) {
            return "error/Exception";
        } catch (NullPointerException e) {
            return "error/Exception";
        }

        return "info";
    }

    @RequestMapping("addCategory")
    public String addCategory(@Valid BoardDto boardDto, BindingResult bindingResult, HttpSession session, Model model) {

        try {


            InfoService infoService = ctx.getBean(InfoService.class);

            infoService.insertCategoryService(boardDto, session);

            SignUpDto userInfo = infoService.selectUserService(session);
            model.addAttribute("userInfo", userInfo);

            List<BoardDto> userInfoCategory = infoService.selectCategoryService(session);
            model.addAttribute("userInfoCategory", userInfoCategory);

            String email = getCheckUser();
            LoginService loginService = ctx.getBean(LoginService.class);
            loginService.userCheckService(email, session);

            if (bindingResult.hasErrors()) {
                return "info";
            }


        } catch (Exception e) {
            return "error/Exception";
        }

        return "info";
    }

    @RequestMapping("deleteCategory")
    public String deleteCategory(@RequestParam("id") String id, @ModelAttribute("boardDto") BoardDto boardDto, HttpSession session, Model model) {
        try {

            InfoService infoService = ctx.getBean(InfoService.class);

            infoService.deleteCategoryService(id, session);

            SignUpDto userInfo = infoService.selectUserService(session);
            model.addAttribute("userInfo", userInfo);

            List<BoardDto> userInfoCategory = infoService.selectCategoryService(session);
            model.addAttribute("userInfoCategory", userInfoCategory);

            String email = getCheckUser();
            LoginService loginService = ctx.getBean(LoginService.class);
            loginService.userCheckService(email, session);


        } catch (Exception e) {
            return "error/Exception";
        }

        return "info";
    }


    public void checkUser(String email) {
        this.email = email;
    }

    public String getCheckUser() {
        return email;
    }


}
