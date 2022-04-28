package notice;

import notice.dao.NoticeDao;
import notice.domain.Notice;
import notice.domain.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

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

    @Test
    public void searchNoticeTest() {
        noticeDao.deleteAllNotice();

        for (int i=1; i<21; i++) {
            Notice notice = new Notice(1,"title"+i,"content","khs6524",new Date(),new Date());
            noticeDao.insertNotice(notice);
        }
        int rowCnt = noticeDao.getCnt(1);
        System.out.println("rowCnt = " + rowCnt);
        assertEquals(rowCnt,20);

        SearchCondition sc = new SearchCondition(1,10,"title2",1,0);
        List<Notice> list = noticeDao.searchNotice(sc);
        System.out.println("list.size() = " + list.size());
        assertEquals(list.size(),2);

        //pageSize가 10이라 최대 10개까지 나옴
        //SearchCondition sc1 = new SearchCondition(1,10,"title1",0,0);
        SearchCondition sc1 = new SearchCondition(1,20,"title1",0,0);
        list = noticeDao.searchNotice(sc1);
        System.out.println("list.size() = " + list.size());
        assertEquals(list.size(),11);

        SearchCondition sc2 = new SearchCondition(1,20,"conte",0,1);
        list = noticeDao.searchNotice(sc2);
        System.out.println("list.size() = " + list.size());
        assertEquals(list.size(),20);

        SearchCondition sc3 = new SearchCondition(1,20,"title",0,2);
        list = noticeDao.searchNotice(sc3);
        System.out.println("list.size() = " + list.size());
        assertEquals(list.size(),20);
    }

    @Test
    public void searchNoticeCntTest() {
        noticeDao.deleteAllNotice();

        for (int i=1; i<21; i++) {
            Notice notice = new Notice(1,"title"+i,"content","khs6524",new Date(),new Date());
            noticeDao.insertNotice(notice);
        }
        int rowCnt = noticeDao.getCnt(1);
        System.out.println("rowCnt = " + rowCnt);
        assertEquals(rowCnt,20);

        SearchCondition sc = new SearchCondition(1,10,"title2",1,0);
        int cnt = noticeDao.searchNoticeCnt(sc);
        System.out.println("cnt = " + cnt);
        assertEquals(cnt,2);

        SearchCondition sc1 = new SearchCondition(1,10,"title2",0,1);
        cnt = noticeDao.searchNoticeCnt(sc1);
        System.out.println("cnt = " + cnt);
        assertEquals(cnt,0);

        SearchCondition sc2 = new SearchCondition(1,10,"title2",1,2);
        cnt = noticeDao.searchNoticeCnt(sc2);
        System.out.println("cnt = " + cnt);
        assertEquals(cnt,2);
    }
}