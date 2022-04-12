package Controller;

public interface UserDao {
    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(String user);

    int deleteAll();

    User selectUser(String user);
}
