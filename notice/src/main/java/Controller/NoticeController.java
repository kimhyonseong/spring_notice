package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @RequestMapping("/write")
    public String write() {
        return "write";
    }

    @PostMapping("/save")
    public String save() {
        return "index";
    }
}
