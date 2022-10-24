package meal.dao;

import meal.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/applicationContext.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    public void insertUser() {
        int rowNum;
        userDao.deleteAll();

        Calendar cal = Calendar.getInstance();
        cal.set(1993,Calendar.SEPTEMBER,20);
        User user = new User("khs","1234","김현성",new Date(cal.getTimeInMillis()));
        rowNum = userDao.insertUser(user);
        assertEquals(1,rowNum);
    }
}