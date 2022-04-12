package Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    public void insertUser() {
        int rowNum;
        userDao.deleteAll();

        Calendar cal = Calendar.getInstance();
        cal.set(1993, Calendar.SEPTEMBER,20);
        User user = new User("khs","1234","김현성",new Date(cal.getTimeInMillis()));
        rowNum = userDao.insertUser(user);
        assertEquals(1, rowNum);
    }

    @Test
    public void updateUser() {
        int rowNum;
        Calendar cal = Calendar.getInstance();
        cal.set(1993,8,20);
        Date date = new Date(cal.getTimeInMillis());
        User user = new User("testUpdate","4321","테스트_아이디",date);

        // 모두 삭제 후 삽입
        userDao.deleteAll();
        rowNum = userDao.insertUser(user);
        assertEquals(1, rowNum);
        System.out.println("user = " + user);

        //패스워드, 날짜 수정
        cal.set(1994,8,20);
        user.setBirth(new Date(cal.getTimeInMillis()));
        user.setPw("1234");
        rowNum = userDao.updateUser(user);
        assertEquals(1, rowNum);

        User user1 = userDao.selectUser(user.getId());
        System.out.println("user1 = " + user1);
    }

    @Test
    public void deleteUser() {
        int rowCnt;
        userDao.deleteAll();
        User user = new User("testDelete","1234","삭제_테스트",new Date());
        rowCnt = userDao.insertUser(user);
        assertEquals(1, rowCnt);
        System.out.println("user = " + user);

        userDao.deleteUser(user.getId());
        User user1 = userDao.selectUser(user.getId());
        System.out.println("user1 = " + user1);
    }

    @Test
    public void selectUser() {
        int rowCnt;
        userDao.deleteAll();
        User user = new User("testSelect","1234","테스트_아이디",new Date());
        rowCnt = userDao.insertUser(user);
        assertEquals(1, rowCnt);

        User user1 = userDao.selectUser(user.getId());
        System.out.println("user1 = " + user1);
    }
}