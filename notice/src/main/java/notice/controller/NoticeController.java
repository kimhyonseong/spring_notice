package notice.controller;

import notice.NoticeValidator;
import notice.dao.NoticeDao;
import notice.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class NoticeController {
    @Autowired
    NoticeDao noticeDao;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //binder.setValidator(new UserValidator());  //로컬
        binder.addValidators(new NoticeValidator());  //validator 추가로
        List<Validator> validatorList = binder.getValidators();
        System.out.println("validatorList = " + validatorList);
    }

    @RequestMapping("/write")
    public String write() {
        return "write";
    }

    @PostMapping("/save")
    public String save(Notice notice) {
        noticeDao.insertNotice(notice);
        return "index";
    }

    @RequestMapping("/list")
    public String list(Model m) {
        List<Notice> list = noticeDao.list();
        m.addAttribute("list",list);
        return "list";
    }
}
