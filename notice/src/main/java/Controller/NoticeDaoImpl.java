package Controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    DataSource ds;

    @Override
    public Notice list() {
        return list(0,10);
    }

    @Override
    public Notice list(int page, int limit) {
        String sql = "select notice_no,notice_code,title,content,user_id,reg_date,up_date " +
                "from notices limit ?,?";
        ResultSet rs = null;
        Notice notice = null;

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setInt(1, (page-1) * limit);
            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                notice = new Notice();
                notice.setNoticeId(rs.getInt(1));
                notice.setNoticeCode(rs.getInt(2));
                notice.setTitle(rs.getString(3));
                notice.setContent(rs.getString(4));
                notice.setWriter(rs.getString(5));
                notice.setContent(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notice;
    }

    @Override
    public Notice selectNotice(int noticeId) {
        String sql = "select notice_no,notice_code,title,content,user_id,reg_date,up_date " +
                "from notice where notice_no = ?";
        ResultSet rs = null;
        Notice notice = null;

        try(
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ) {
            pstmt.setInt(1,noticeId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                notice = new Notice();
                notice.setNoticeId(rs.getInt(1));
                notice.setNoticeCode(rs.getInt(2));
                notice.setTitle(rs.getString(3));
                notice.setContent(rs.getString(4));
                notice.setWriter(rs.getString(5));
                notice.setRegDate(new Date(rs.getDate(6).getTime()));
                notice.setUpDate(new Date(rs.getDate(7).getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notice;
    }

    @Override
    public int deleteNotice(int noticeId) {
        int rowCnt = 0;
        String sql = "delete from notices where notice_id = ?";

        try(Connection conn = ds.getConnection();
            PreparedStatement pstmp = conn.prepareStatement(sql);
        ) {
            pstmp.setInt(1,noticeId);
            rowCnt = pstmp.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Override
    public int deleteAllNotice() {
        int rowCnt = 0;
        String sql = "delete from notice";

        try(Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            rowCnt = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int insertNotice(Notice notice) {
        int rowCnt = 0;
        String sql = "insert into notice(notice_code,title,content,user_id,reg_date,up_date)" +
                " values(?,?,?,?,now(),now())";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstsm = conn.prepareStatement(sql);
        ) {
            pstsm.setInt(1,notice.getNoticeCode());
            pstsm.setString(2, notice.getTitle());
            pstsm.setString(3,notice.getContent());
            pstsm.setString(4,notice.getWriter());

            rowCnt = pstsm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int updateNotice(Notice notice) {
        int rowCnt = 0;
        String sql = "update notice set notice_code = ?,title = ?, content = ?" +
                ", up_date = now() where notice_no = ?";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setInt(1,notice.getNoticeCode());
            pstmt.setString(2,notice.getTitle());
            pstmt.setString(3,notice.getContent());
            pstmt.setInt(4,notice.getNoticeId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }
}
