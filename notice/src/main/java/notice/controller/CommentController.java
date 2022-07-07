package notice.controller;

import notice.dao.CommentDao;
import notice.domain.Comment;
import notice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments/{bno}")
    public ResponseEntity<List<Comment>> view(@PathVariable Integer bno) {
        List<Comment> list = null;
        System.out.println(bno);
        try {
            list = commentService.view(bno);
            System.out.println("list = " + list);
            return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Comment>>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<String> insert(@RequestBody Comment comment, Integer bno, HttpSession session) {
        int rowCnt;
        comment.setWriter((String) session.getAttribute("id"));

        try {
            System.out.println("comment = " + comment);
            rowCnt = commentService.write(comment);

            if (rowCnt != 1)
                throw new Exception("write fail.");

            return new ResponseEntity<String>("WRT_OK",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR",HttpStatus.BAD_REQUEST);
        }
    }
}
