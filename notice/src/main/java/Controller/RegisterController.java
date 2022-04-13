package Controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.setValidator(new UserValidator());
//    }

    @RequestMapping("/add")
    public String add(){
        return "register";
    }

    @PostMapping("/save")
    public String save(User user, BindingResult result, Model m){
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user,result);

        System.out.println("user = " + user);
        System.out.println("result = " + result);

        if (result.hasErrors()) {
            return "redirect:/register/add";
        }

        return "registerSuccess";
    }

    @RequestMapping("/success")
    public String success() {
        return "registerSuccess";
    }
}
