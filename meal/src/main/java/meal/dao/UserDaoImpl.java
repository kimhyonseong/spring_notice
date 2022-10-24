package meal.dao;

import meal.domain.User;
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
        int rowCnt = 0;

        String sql = "insert into user(id,pw,name,reg_date) values(?,?,?,now())";

        try(
                Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ) {
            pstmt.setString(1,user.getId());
            pstmt.setString(2,user.getPw());
            pstmt.setString(3,user.getName());

            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return rowCnt;
    }

    @Override
    public int updateUser(User user) {
        int rowCnt = 0;

        String sql = "update user set pw=?, name=? where id=?";

        try(
                Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            pstmt.setString(1,user.getId());
            pstmt.setString(2,user.getPw());

            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return rowCnt;
    }

    @Override
    public int deleteUser(String id) {
        int rowCnt=0;
        String sql = "delete from user where id=?";

        try(
                Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ) {
            pstmt.setString(1,id);
            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return rowCnt;
    }

    @Override
    public User selectUser(String id) {
        String sql = "select id,pw,name,reg_date from user where id = ?";
        User user = null;
        ResultSet rs = null;

        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getString(1));
                user.setPw(rs.getString(2));
                user.setName(rs.getString(3));
                user.setRegDate(new Date(rs.getDate(3).getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public int deleteAll(){
        String sql = "delete from user";

        try(
                Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ) {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
