package notice;

import notice.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class GlobalUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error){
        System.out.println("GlobalUserValidator is call");

        User user = (User)target;

        ValidationUtils.rejectIfEmpty(error,"id","empty","id is empty");
        ValidationUtils.rejectIfEmpty(error,"pw","empty","pw is empty");
        ValidationUtils.rejectIfEmpty(error,"name","empty","name is empty");
        ValidationUtils.rejectIfEmpty(error,"birth","empty","birth is empty");

        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{4,10}$");

        if(!pattern.matcher(user.getId()).matches()) {
            error.rejectValue("id","check","check the id");
        }
    }
}
