package notice;

import notice.domain.Notice;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NoticeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Notice.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors error) {
        System.out.println("NoticeValidator is call");

        Notice notice = (Notice) obj;

        ValidationUtils.rejectIfEmpty(error,"title","empty","title is empty");
        ValidationUtils.rejectIfEmpty(error,"content","empty","content is empty");
        //ValidationUtils.rejectIfEmpty(error,"title","empty","title is empty");
    }
}
