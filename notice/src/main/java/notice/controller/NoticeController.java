package notice.controller;

import notice.NoticeValidator;
import notice.dao.NoticeDao;
import notice.domain.Notice;
import notice.domain.PageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class NoticeController {
    @Autowired
    NoticeDao noticeDao;

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        //binder.setValidator(new UserValidator());  //로컬
//        binder.addValidators(new NoticeValidator());  //validator 추가로
//        List<Validator> validatorList = binder.getValidators();
//        System.out.println("validatorList = " + validatorList);
//    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @GetMapping("/view")
    public String view(int bno,int currentPage, Model m) {
        Notice notice = noticeDao.selectNotice(bno);
        m.addAttribute("notice",notice);
        m.addAttribute("currentPage",currentPage);
        return "write";
    }

    @PostMapping("/save")
    public String save(Notice notice) {
        noticeDao.insertNotice(notice);
        return "index";
    }

    @RequestMapping("/list")
    public String list(Integer currentPage,Model m) {
        if (currentPage == null) currentPage = 1;
        int limit = 10;
        List<Notice> list = noticeDao.list(currentPage,limit);
        PageHandler pageHandler = new PageHandler(noticeDao.getCnt(),currentPage,limit);
        System.out.println(pageHandler);
        m.addAttribute("list",list);
        m.addAttribute("currentPage",currentPage);
        m.addAttribute("paging",pageHandler);
        return "list";
    }
}
