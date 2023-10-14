package ru.stepic.web.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stepic.web.acounts.AccountService;
import ru.stepic.web.acounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component//cntr shift enter комбинация ускоряет создание циклов и т. п.
public class AuthService {
    private final AccountService accountService;


    @Autowired
    public AuthService(AccountService accountService) {
        this.accountService = accountService;
    }

    public double calculatorService(String a, String b, String action){
        int i = Integer.parseInt(a.trim());
        int j = Integer.parseInt(b.trim());

        double result;


        switch (action) {
            case "multiplication":
                result = i * j;
                break;
            case "addition":
                result = i + j;
                break;
            case "subtraction":
                result = i - j;
                break;
            case "division":
                result = i / (double) j;
                break;
            default:
                result = 0;
                System.out.println("not result");

        }
        return result;
    }

    public void doAuthService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        {
            if (login == null || pass == null) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            UserProfile profile = accountService.getUserByLogin(login);
            if (profile == null || !profile.getPassIndex(1).equals(pass)) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            accountService.addSession(request.getSession().getId(), profile);
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }


//        public void doDelete (HttpServletRequest ){
//            String sessionId = request.getSession().getId();
//            UserProfile profile = accountService.getUserBySessionId(sessionId);
//            if (profile == null) {
//                response.setContentType("text/html;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            } else {
//                accountService.deleteSession(sessionId);
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().println("Goodbye");
//                response.setStatus(HttpServletResponse.SC_OK);
//            }
//        }
    }
}
