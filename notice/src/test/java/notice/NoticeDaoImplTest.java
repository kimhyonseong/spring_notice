package notice;

import notice.dao.NoticeDao;
import notice.domain.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class NoticeDaoImplTest {
    @Autowired
    NoticeDao noticeDao;
    @Test
    public void insertTest() {
        noticeDao.deleteAllNotice();

        for (int i=0; i<200; i++) {
            Notice notice = new Notice(1,"title"+i,"content","khs",new Date(),new Date());
            noticeDao.insertNotice(notice);
        }
    }

}