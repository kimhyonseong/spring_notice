package notice.dao;

import notice.domain.Comment;
import notice.domain.Notice;
import notice.domain.SearchCondition;
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
public class CommentDaoImplTest {
    @Autowired
    CommentDao commentDao;
    @Autowired
    NoticeDao noticeDao;

    @Test
    public void write() {
        int rowCnt = 0;
        int noticeId = 0;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();
        List<Notice> list = new ArrayList<>();

        Notice notice = new Notice(1,"test_title","test_contents","khs6524",new Date(),new Date());
        System.out.println("notice = " + notice);
        rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1, rowCnt);

        SearchCondition sc = new SearchCondition(1,1,"",0,0);
        list = noticeDao.searchNotice(sc);
        System.out.println("list = " + list);
        noticeId = list.get(0).getNoticeId();
        assertEquals(1,list.size());

        Comment comment = new Comment(noticeId,"hello","khs6524");
        System.out.println("comment = " + comment);
        rowCnt = commentDao.write(comment);
        assertEquals(1,rowCnt);
    }

    @Test
    public void commentList() {
        commentDao.deleteAll();
        int rowCnt = 0;
        List<Comment> list = new ArrayList<>();

        // 댓글 쓰기
        Comment comment = new Comment(1,"hello","khs6524");
        rowCnt = commentDao.write(comment);
        assertEquals(1, rowCnt);

        // 리스트 확인
        list = commentDao.commentList(1);
        assertEquals("hello",list.get(0).getComment());
    }

    @Test
    public void update() {
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();
        int rowCnt = 0;
        int noticeId = 0;
        int commentId = 0;
        List<Notice> noticeList = null;
        List<Comment> commentList = null;

        // 게시글 삽입
        Notice notice = new Notice(1,"test_title","test_contents","khs6524",new Date(),new Date());
        rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1,rowCnt);

        // 게시글 확인
        noticeList = noticeDao.searchNotice(new SearchCondition(1,1,"",0,0));
        noticeId = noticeList.get(0).getNoticeId();
        System.out.println("noticeId = " + noticeId);
        assertTrue(noticeId != 0);

        // 댓글 삽입
        Comment comment = new Comment(noticeId,"hello","khs6524");
        rowCnt = commentDao.write(comment);
        assertEquals(1, rowCnt);

        // 댓글 cno 알아내기
        commentList = commentDao.commentList(noticeId);
        commentId = commentList.get(0).getCno();
        System.out.println("commentId = " + commentId);
        assertTrue(commentId != 0);

        // 댓글 업데이트
        Comment comment2 = new Comment(0,"hello_update","khs6524");
        comment2.setCno(commentId);
        rowCnt = commentDao.update(comment2);
        assertEquals(1, rowCnt);
    }

    @Test
    public void deleteAll() {
        int rowCnt = 0;
        commentDao.deleteAll();

        rowCnt = commentDao.getAllCnt();
        assertEquals(0,rowCnt);
    }

    @Test
    public void delete() {
        int rowCnt = 0;
        int noticeId = 0;
        int commentId = 0;
        List<Notice> noticeList = null;
        List<Comment> commentList = null;
        noticeDao.deleteAllNotice();
        commentDao.deleteAll();

        // 게시글 삽입
        Notice notice = new Notice(1,"hello","content","khs6524",new Date(),new Date());
        rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1, rowCnt);

        // 게시글 아이디 조회
        noticeList = noticeDao.searchNotice(new SearchCondition(1,1,"",1,0));
        noticeId = noticeList.get(0).getNoticeId();
        System.out.println("noticeId = " + noticeId);
        assertTrue(noticeId != 0);

        // 댓글 생성
        Comment comment = new Comment(noticeId,"Hey","khs6524");
        rowCnt = commentDao.write(comment);
        assertEquals(rowCnt,1);

        // 댓글 고유번호 조회
        commentList = commentDao.commentList(noticeId);
        commentId = commentList.get(0).getCno();
        System.out.println("commentId = " + commentId);
        assertTrue(commentId != 0);

        // 댓글 지우기
        Comment comment2 = new Comment(noticeId,"test","khs6524");
        comment2.setCno(commentId);
        rowCnt = commentDao.delete(comment2);
        assertEquals(rowCnt, 1);
    }
}