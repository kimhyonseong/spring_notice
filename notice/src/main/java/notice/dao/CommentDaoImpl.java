package notice.dao;

import notice.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    DataSource ds;

    @Override
    public int write(Comment comment) {
        String sql = "insert into comment(bno,comment,user_id) values(?,?,?)";
        int rowCnt = 0;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1,comment.getBno());
            pstmt.setString(2, comment.getComment());
            pstmt.setString(3,comment.getWriter());

            rowCnt = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Override
    public List<Comment> commentList(Integer bno) {
        String sql = "select cno,bno,pcno,comment,user_id,reg_date,up_date from comment " +
                "where bno = ? order by reg_date desc";
        List<Comment> list = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1,bno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCno(rs.getInt(1));
                comment.setBno(rs.getInt(2));
                comment.setPcno(rs.getInt(3));
                comment.setComment(rs.getString(4));
                comment.setWriter(rs.getString(5));
                comment.setReg_date(new Date(rs.getDate(6).getTime()));
                comment.setUp_date(new Date(rs.getDate(7).getTime()));

                list.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Comment comment) {
        int rowCnt = 0;
        String sql = "update comment set comment = ?, up_date = now() where cno = ? and user_id = ?";

        try(Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1,comment.getComment());
            pstmt.setInt(2,comment.getCno());
            pstmt.setString(3,comment.getWriter());
            rowCnt = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int deleteAll() {
        String sql = "delete from comment";
        int rowCnt = 0;

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            rowCnt = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int delete(Comment comment) {
        String sql = "delete from comment where cno = ? and user_id = ?";
        int rowCnt = 0;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1,comment.getCno());
            pstmt.setString(2,comment.getWriter());
            rowCnt = pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int getCnt(Integer bno){
        int rowCnt = 0;
        String sql = "select count(*) from comment where bno = ?";
        ResultSet rs = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1,bno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rowCnt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int getAllCnt(){
        int rowCnt = 0;
        String sql = "select count(*) from comment";
        ResultSet rs = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rowCnt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }
}
