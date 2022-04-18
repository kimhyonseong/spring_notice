package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //binder.setValidator(new UserValidator());  //로컬
        //binder.addValidators(new UserValidator());  //validator 추가로
        List<Validator> validatorList = binder.getValidators();
        System.out.println("validatorList = " + validatorList);
    }

    @RequestMapping("/add")
    public String add(){
        return "register";
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "register";
        }

        userDao.insertUser(user);
        //userService.registerUser(user);  // 중복체크 하기

        return "registerSuccess";
    }

    @RequestMapping("/success")
    public String success() {
        return "registerSuccess";
    }
}
