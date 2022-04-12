package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Controller
public class LoginController {
    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(String id, String pw, HttpServletRequest request) {
        if(loginCheck(id, pw)) {
            HttpSession session = request.getSession();
            session.setAttribute("id",id);

            return "redirect:/";
        }

        return "loginForm";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //아이디, 패스워드 둘다 일치하면 패스
    private boolean loginCheck(String id, String pw) {
        User user = userDao.selectUser(id);

        return user.getId().equals(id) && user.getPw().equals(pw);
    }
}
