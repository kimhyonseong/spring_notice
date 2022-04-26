package notice.controller;

import notice.domain.User;
import notice.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.util.ObjectUtils.isEmpty;

@Controller
public class LoginController {
    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String loginForm(String returnUrl, Model m) {
        m.addAttribute("returnUrl",returnUrl);
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(String id, String pw, HttpServletRequest request,String returnUrl) {
        if(loginCheck(id, pw)) {
            HttpSession session = request.getSession();
            session.setAttribute("id",id);

            System.out.println("returnUrl = " + returnUrl);

            if (isEmpty(returnUrl)) {
                return "redirect:/";
            } else {
                return "redirect:"+returnUrl;
            }
        }

        return "loginForm";
    }

    @GetMapping("/logout")
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
