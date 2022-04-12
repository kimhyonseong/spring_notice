package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource ds;

    @Override
    public int insertUser(User user) {
        int rowCnt;
        String sql = "insert into users(user_id,pw,name,birth) values(?,?,?,?)";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getName());
            pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));

            rowCnt = pstmt.executeUpdate();
        } catch(SQLException e) {
            return 0;
        }
        return rowCnt;
    }

    @Override
    public int updateUser(User user) {
        int rowCnt;
        String sql = "update users set pw = ?, name = ?, birth = ? " +
                "where user_id = ?";

        try(
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setString(1,user.getPw());
            pstmt.setString(2,user.getName());
            pstmt.setDate(3, new java.sql.Date(user.getBirth().getTime()));
            pstmt.setString(4,user.getId());

            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return rowCnt;
    }

    @Override
    public int deleteUser(String user) {
        int rowCnt;
        String sql = "delete from users where user_id = ?";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1,user);
            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return rowCnt;
    }

    @Override
    public int deleteAll() {
        String sql = "delete from users";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public User selectUser(String userId) {
        String sql = "select user_id,pw,name,birth from users where user_id = ?";
        ResultSet rs = null;
        User users = null;

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1,userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                users = new User();
                users.setId(rs.getString(1));
                users.setPw(rs.getString(2));
                users.setName(rs.getString(3));
                users.setBirth(new Date(rs.getDate(4).getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return users;
    }
}
