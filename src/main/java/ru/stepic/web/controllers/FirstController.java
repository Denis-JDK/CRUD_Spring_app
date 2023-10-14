package ru.stepic.web.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stepic.web.acounts.AccountService;
import ru.stepic.web.acounts.UserProfile;
import ru.stepic.web.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class FirstController {

    private final AccountService accountService;
    @Autowired
    private AuthService authService;
    @Autowired
    public FirstController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            (@RequestParam ("login") String login,
//             @RequestParam ("pass") String pass)
//        System.out.println("You login "+ login + " and pass "+ pass);
       authService.doAuthService(request, response);
        return "first/hello";
    }
    @GetMapping("/th")
    public String patternPage(@RequestParam(value = "login", required = false) String login,
                              @RequestParam(value = "pass", required = false) String pass,
                              Model model){
        model.addAttribute("message", "Yuer login " + login+" and pass "+ pass);
        return "first/hello";
    }
    @PostMapping("/calculator")
    public String calculator(@RequestParam("a") String a,
                             @RequestParam("b") String b,
                             @RequestParam("action") String action, Model model){
        double answer = authService.calculatorService(a,b,action);
        model.addAttribute("result", answer);
        return "first/calculator";

    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }


}
