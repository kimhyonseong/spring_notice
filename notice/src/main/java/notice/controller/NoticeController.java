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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.ObjectUtils.isEmpty;

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

    @GetMapping("/view")
    public String view(int bno,int currentPage, Model m, HttpSession session) {
        Notice notice = noticeDao.selectNotice(bno);
        String writer = (String) session.getAttribute("id");
        ArrayList<String> codeList = new ArrayList<String>(Arrays.asList("공지","자유","익명"));

        m.addAttribute("codeList",codeList);
        m.addAttribute("login",writer);
        m.addAttribute("notice",notice);
        m.addAttribute("currentPage",currentPage);
        return "write";
    }

    @GetMapping("/write")
    public String write(HttpSession session,Model m) {
        String writer = (String) session.getAttribute("id");
        ArrayList<String> codeList = new ArrayList<String>(Arrays.asList("공지","자유","익명"));

        try {
            if (isEmpty(writer)) {
                throw new Exception("no login");
            }
            // 위에서 하면 returnUrl에 파라미터로 들어가버림
            m.addAttribute("writer",writer);
            m.addAttribute("codeList",codeList);
            m.addAttribute("mode","new");
            return "write";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?returnUrl=/board/write";
        }
    }

    @PostMapping("/write")
    public String save(Notice notice,HttpSession session, RedirectAttributes rattr) {
        notice.setWriter((String) session.getAttribute("id"));
        System.out.println("notice = " + notice);

        try {
            int rowCnt = noticeDao.insertNotice(notice);

            if (rowCnt != 1) throw new Exception("fail");

            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "WRT_ERR");
            e.printStackTrace();
            return "write";
        }
    }

    @PostMapping("/delete")
    public String delete(Notice notice, Model m, HttpSession session, RedirectAttributes rattr) {
        Notice notice1 = noticeDao.selectNotice(notice.getNoticeId());

        try {
            String writer = (String) session.getAttribute("id");

            // 아이디 확인
           if (!Objects.equals(writer, notice1.getWriter()))
                throw new Exception("No match Id.");

            int rowCnt = noticeDao.deleteNotice(notice.getNoticeId(),writer);

            if (rowCnt != 1) throw new Exception("Board delete error.");

            rattr.addFlashAttribute("msg","DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DEL_ERR");
        }

        return "redirect:/board/list";
    }

    @RequestMapping("/list")
    public String list(Integer currentPage,Model m) {
        if (currentPage == null) currentPage = 1;
        int limit = 10;
        List<Notice> list = noticeDao.list(currentPage,limit);
        PageHandler pageHandler = new PageHandler(noticeDao.getCnt(),currentPage,limit);

        m.addAttribute("list",list);
        m.addAttribute("currentPage",currentPage);
        m.addAttribute("paging",pageHandler);
        return "list";
    }
}
