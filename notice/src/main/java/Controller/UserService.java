package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.Objects;

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
