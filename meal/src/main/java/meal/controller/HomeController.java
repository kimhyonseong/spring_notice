package meal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String main() {
        return "index";
    }

    @RequestMapping("/eat")
    public String main2() {
        return "eat";
    }
}
