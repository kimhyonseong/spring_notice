package notice;

import notice.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors error){
        System.out.println("UserValidator is call");

        User user = (User) obj;

        ValidationUtils.rejectIfEmpty(error,"id","empty","id is empty");
        ValidationUtils.rejectIfEmpty(error,"pw","empty","pw is empty");
        ValidationUtils.rejectIfEmpty(error,"name","empty","name is empty");
        ValidationUtils.rejectIfEmpty(error,"birth","empty","birth is empty");

        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{4,10}$");

        if(!pattern.matcher(user.getId()).matches()) {
            error.rejectValue("id","check","check th id");
        }
    }
}
