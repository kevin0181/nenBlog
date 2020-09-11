package blog.nen.controller;

import blog.nen.Exception.Exception;
import blog.nen.Exception.SameEmailException;
import blog.nen.config.DBConfig;
import blog.nen.config.UserConfig;
import blog.nen.dao.LoginDto;
import blog.nen.dao.SignUpDto;
import blog.nen.service.SignUpService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfig.class, DBConfig.class);

    @GetMapping("index")
    public String index() {
        //redirect 시 '/'가 없으면 맵핑값을 기준으로 redirect 됨
        return "redirect:";
    }

    @PostMapping("login")
    public String login(LoginDto loginDao) {
        //로그인 시 메인페이지로
        return "main";
    }


    @GetMapping("signPage")
    public String signPage() {
        //회원 가입 페이지
        return "signPage";
    }

    @PostMapping("signUp")
    public String signUp(SignUpDto signUpDto) {
        //회원 가입 후 index 페이지로 리다이렉트
        SignUpService signUpService = ctx.getBean(SignUpService.class);
        try {
            signUpService.signUp(signUpDto);
        } catch (SameEmailException e) {
            return "Exception";
        } catch (Exception e) {
            return "Exception";
        }
        return "redirect:/index";
    }

}
