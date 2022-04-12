package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @RequestMapping("/add")
    public String add(){
        return "register";
    }

    @PostMapping("/save")
    public String save(User user, Model m){
        if (!isValue(user)) {
            return "redirect:/register/add";
        }
        return "registerSuccess";
    }

    private boolean isValue(User user) {
        return true;
    }

    @RequestMapping("/success")
    public String success() {
        return "registerSuccess";
    }
}
