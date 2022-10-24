package meal.dao;

import meal.domain.User;

public interface UserDao {
    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(String id);

    User selectUser(String id);

    int deleteAll();
}
