package Controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class RegisterValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors error){
        ValidationUtils.rejectIfEmpty(error,"id","user.id.empty","id is empty");

        User user = (User) obj;

        Pattern pattern = Pattern.compile("");
    }
}
