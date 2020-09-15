package blog.nen.controller;

import blog.nen.Exception.Exception;
import blog.nen.Exception.NotFoundException;
import blog.nen.Exception.SameEmailException;
import blog.nen.config.DBConfig;
import blog.nen.config.UserConfig;
import blog.nen.dto.LoginDto;
import blog.nen.dto.SignUpDto;
import blog.nen.service.LoginService;
import blog.nen.service.SignUpService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfig.class, DBConfig.class);
    private ModelAndView mav = new ModelAndView();

    @GetMapping("index")
    public String index() {
        //redirect 시 '/'가 없으면 맵핑값을 기준으로 redirect 됨
        return "redirect:";
    }

    @PostMapping("main")
    public String login(LoginDto loginDto, HttpSession session, Model model) {
        //로그인 시 메인페이지로
        try {
            LoginService loginService = ctx.getBean(LoginService.class);
            loginService.loginService(loginDto, session);

        } catch (NotFoundException e) {
            model.addAttribute("ExceptionName", "NotFoundException");
            return "Exception";
        }
        return "main";
    }

    @GetMapping("main")
    public String mainGetException(Model model) {
        //주소창에 쳤을때 익셉션
        model.addAttribute("ExceptionName", "mainGetException");
        return "Exception";
    }


    @GetMapping("signPage")
    public String signPage() {
        //회원 가입 페이지로 이동
        return "signPage";
    }

    @PostMapping("signUp")
    public String signUp(SignUpDto signUpDto, Model model) {
        //회원 가입 후 index 페이지로 리다이렉트
        SignUpService signUpService = ctx.getBean(SignUpService.class);
        try {
            signUpService.signUpService(signUpDto);
        } catch (SameEmailException e) {
            model.addAttribute("ExceptionName", "sameEmailException");
            return "Exception";
        } catch (Exception e) {
            model.addAttribute("ExceptionName", "Exception");
            return "Exception";
        }
        return "redirect:index";
    }

    @GetMapping("signUp")
    public String signUpGetException(Model model) {
        // signUp 주소창으로 입력했을때 익셉션
        model.addAttribute("ExceptionName", "singUpGetException");
        return "Exception";
    }

}
