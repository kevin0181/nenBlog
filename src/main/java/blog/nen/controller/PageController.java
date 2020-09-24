package blog.nen.controller;

import blog.nen.Exception.Exception;
import blog.nen.Exception.NotFoundException;
import blog.nen.Exception.SameEmailException;
import blog.nen.Exception.WrongException;
import blog.nen.config.DBConfig;
import blog.nen.config.UserConfig;
import blog.nen.dto.LoginDto;
import blog.nen.dto.SignUpDto;
import blog.nen.service.LoginService;
import blog.nen.service.SignUpService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PageController {

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfig.class, DBConfig.class);

    @RequestMapping("/")
    public String startPage(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "index";
    }

    @RequestMapping("index")
    public String index(HttpSession session) {
        //redirect 시 '/'가 없으면 맵핑값을 기준으로 redirect 됨
        if (session.getAttribute("userLogin") == null)
            return "redirect:";

        return "main";
    }

    @RequestMapping("main")
    public String login(@Valid LoginDto loginDto, BindingResult bindingResult, HttpSession session) {
        //로그인 시 메인페이지로

        if (bindingResult.hasErrors()) {
            return "index";
        }

        try {
            LoginService loginService = ctx.getBean(LoginService.class);
            loginService.loginService(loginDto, session);

        } catch (NotFoundException e) {
            bindingResult.rejectValue("ErrorCode", "notFound.Error");
            return "index";
        } catch (WrongException e) {
            bindingResult.rejectValue("ErrorCode", "Wrong.Error");
            return "index";
        }
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

    @GetMapping("main")
    public String mainGet(HttpSession session, Model model) {
        //주소창에 쳤을때 익셉션
        if (session.getAttribute("userLogin") == null) {
            model.addAttribute("ExceptionName", "mainGetException");
            return "error/Exception";
        }

        return "main";
    }

}
