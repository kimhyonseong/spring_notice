package notice;

import notice.domain.User;
import notice.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class UserService {
    @Autowired
    DataSource ds;

    @Autowired
    UserDao userDao;

    public void registerUser(User user) throws Exception {

        try {
           if (userDao.existId(user.getId()) != 1) {
               userDao.insertUser(user);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
