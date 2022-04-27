package notice;

import notice.dao.NoticeDao;
import notice.domain.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class NoticeDaoImplTest {
    @Autowired
    NoticeDao noticeDao;
    @Test
    public void insertTest() {
        noticeDao.deleteAllNotice();

        for (int i=0; i<200; i++) {
            Notice notice = new Notice(1,"title"+i,"content","khs6524",new Date(),new Date());
            noticeDao.insertNotice(notice);
        }
    }

    @Test
    public void deleteTest() {
        noticeDao.deleteAllNotice();

        Notice notice = new Notice(1,"test1","content","khs6524",new Date(),new Date());
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1, rowCnt);

        int noticeId = noticeDao.list().get(0).getNoticeId();

        rowCnt = noticeDao.deleteNotice(noticeId,"khs6524");
        assertEquals(1, rowCnt);
    }

    @Test
    public void updateTest() {
        noticeDao.deleteAllNotice();

        Notice notice = new Notice(1,"test1","content","khs6524",new Date(),new Date());
        System.out.println("notice = " + notice);
        int rowCnt = noticeDao.insertNotice(notice);
        assertEquals(1, rowCnt);

        int noticeId = noticeDao.list().get(0).getNoticeId();
        System.out.println("noticeId = " + noticeId);

        Notice notice1 = new Notice(2,"updated","content1234","khs6524",new Date(),new Date());
        notice1.setNoticeId(noticeId);
        System.out.println("notice1 = " + notice1);
        rowCnt = noticeDao.updateNotice(notice1);
        assertEquals(rowCnt,1);
    }
}