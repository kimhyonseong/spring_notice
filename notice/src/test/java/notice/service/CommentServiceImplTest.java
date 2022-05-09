package notice.service;

import notice.dao.*;
import notice.domain.*;
import notice.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class CommentServiceImplTest {
    @Autowired
    NoticeDao noticeDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    CommentService commentService;

    @Test
    public void view() {
        List<Notice> list = new ArrayList<>();
        List<Comment> clist = new ArrayList<>();
        String testString = "";
        int bno = 0;
        int res = 0;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();

        // 글 삽입
        Notice notice = new Notice(1,"title","content","khs6524",new Date(),new Date());
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1,rowCnt);

        list = noticeDao.list();
        testString = list.get(0).getTitle();
        assertEquals("title",testString);

        bno = list.get(0).getNoticeId();
        System.out.println("bno = " + bno);

        Comment comment = new Comment(bno,"comment","khs6524");

        try {
            //commentService.CommentServiceImpl(notice,comment);
            res = commentService.write(comment);

            assertEquals(1,res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //댓글 들어간거 확인
        clist = commentService.view(bno);
        testString = clist.get(0).getComment();
        assertEquals(testString,"comment");

        //게시글 댓글 개수 확인
        Notice notice1 = noticeDao.selectNotice(bno);
        res = notice1.getCmt_cnt();
        System.out.println("res = " + res);
        assertEquals(1,res);
    }

    @Test
    public void write() {
        List<Notice> list = new ArrayList<>();
        List<Comment> clist = new ArrayList<>();
        String testString = "";
        int bno = 0;
        int res = 0;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();

        // 글 삽입
        Notice notice = new Notice(1,"title","content","khs6524",new Date(),new Date());
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1,rowCnt);

        // 글 확인
        list = noticeDao.list();
        testString = list.get(0).getTitle();
        assertEquals("title",testString);

        // 글 아이디 가져옴
        bno = list.get(0).getNoticeId();
        System.out.println("bno = " + bno);

        // 댓글 삽입
        Comment comment = new Comment(bno,"comment","khs6524");
        try {
            res = commentService.write(comment);
            assertEquals(1,res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Notice notice1 = noticeDao.selectNotice(bno);
        res = notice1.getCmt_cnt();
        System.out.println("cmt = " + res);
        assertEquals(1,res);
    }

    @Test
    public void delete() throws Exception{
        List<Notice> list = new ArrayList<>();
        List<Comment> clist = new ArrayList<>();
        String testString = "";
        int bno;
        int cno;
        int res;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();

        // 글 삽입
        Notice notice = new Notice(1,"title","content","khs6524",new Date(),new Date());
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1,rowCnt);

        // 글 확인
        list = noticeDao.list();
        testString = list.get(0).getTitle();
        assertEquals("title",testString);

        // 글 아이디 가져옴
        bno = list.get(0).getNoticeId();
        System.out.println("bno = " + bno);

        // 댓글 삽입
        Comment comment = new Comment(bno,"comment","khs6524");
        res = commentService.write(comment);
        assertEquals(1,res);

        //댓글 개수확인
        Notice notice1 = noticeDao.selectNotice(bno);
        res = notice1.getCmt_cnt();
        System.out.println("cmt_cnt = " + res);
        assertEquals(1, res);

        // 댓글 지우기
        clist = commentDao.commentList(bno);
        cno = clist.get(0).getCno();
        Comment comment1 = new Comment();
        comment1.setBno(bno);
        comment1.setCno(cno);
        comment1.setWriter("khs6524");
        res = commentService.delete(comment1);
        assertEquals(1, res);

        //댓글개수 확인
        Notice notice2 = noticeDao.selectNotice(bno);
        res = notice2.getCmt_cnt();
        System.out.println("cmt_cnt = " + res);
        assertEquals(0,res);
    }

    @Test
    public void update() throws Exception {
        List<Notice> list = new ArrayList<>();
        List<Comment> clist = new ArrayList<>();
        String testString = "";
        int bno;
        int cno;
        int res;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();

        // 글 삽입
        Notice notice = new Notice(1,"title","content","khs6524",new Date(),new Date());
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1,rowCnt);

        // 글 확인
        list = noticeDao.list();
        testString = list.get(0).getTitle();
        assertEquals("title",testString);

        // 글 아이디 가져옴
        bno = list.get(0).getNoticeId();
        System.out.println("bno = " + bno);

        // 댓글 삽입
        Comment comment = new Comment(bno,"comment","khs6524");
        res = commentService.write(comment);
        assertEquals(1,res);

        //댓글 개수확인
        res = commentService.getCnt(bno);
        System.out.println("cmt_cnt = " + res);
        assertEquals(1, res);

        //댓글 아이디 알아내기
        clist = commentDao.commentList(bno);
        cno = clist.get(0).getCno();

        //댓글 업데이트
        Comment comment1 = new Comment();
        comment1.setComment("modify");
        comment1.setCno(cno);
        comment1.setWriter("khs6524");
        commentService.update(comment1);

        //댓글 확인
        clist = commentDao.commentList(bno);
        testString = clist.get(0).getComment();
        System.out.println("update comment = " + testString);
        assertEquals("modify",testString);
    }

    @Test
    public void getCnt() throws Exception {
        List<Notice> list = new ArrayList<>();
        List<Comment> clist = new ArrayList<>();
        String testString = "";
        int bno;
        int cno;
        int res;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();

        // 글 삽입
        Notice notice = new Notice(1,"title","content","khs6524",new Date(),new Date());
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1,rowCnt);

        // 글 확인
        list = noticeDao.list();
        testString = list.get(0).getTitle();
        assertEquals("title",testString);

        // 글 아이디 가져옴
        bno = list.get(0).getNoticeId();
        System.out.println("bno = " + bno);

        // 댓글 삽입
        Comment comment = new Comment(bno,"comment","khs6524");
        res = commentService.write(comment);
        assertEquals(1,res);

        //댓글 개수확인
//        res = noticeDao.commentCnt(bno);
//        System.out.println("cmt_cnt = " + res);
//        assertEquals(1, res);
        res = commentService.getCnt(bno);
        System.out.println("cmt_cnt = " + res);
        assertEquals(1, res);
    }
}