package notice.dao;

import notice.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    DataSource ds;

    @Override
    public List<Notice> list() {
        return list(1,10);
    }

    @Override
    public List<Notice> list(int page, int limit) {
        List<Notice> list = new ArrayList<Notice>();
        String sql = "select notice_no,notice_code,title,content,user_id,reg_date,up_date " +
                "from notice order by notice_no desc limit ?,?";
        ResultSet rs = null;
        System.out.println(sql);
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setInt(1, (page-1) * limit);
            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNoticeId(rs.getInt(1));
                notice.setNoticeCode(rs.getInt(2));
                notice.setTitle(rs.getString(3));
                notice.setContent(rs.getString(4));
                notice.setWriter(rs.getString(5));
                notice.setRegDate(new Date(rs.getDate(6).getTime()));
                notice.setUpDate(new Date(rs.getDate(7).getTime()));

                list.add(notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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
    public int getCnt(){
        String sql = "select count(*) as cnt from notice";
        ResultSet rs;
        int totalCnt = 0;
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCnt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCnt;
    }

    @Override
    public int deleteNotice(int noticeId, String writer) {
        int rowCnt = 0;
        String sql = "delete from notice where notice_no = ? and user_id = ?";

        try(Connection conn = ds.getConnection();
            PreparedStatement pstmp = conn.prepareStatement(sql);
        ) {
            pstmp.setInt(1,noticeId);
            pstmp.setString(2,writer);
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
                ", up_date = now() where notice_no = ? and user_id = ?";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setInt(1,notice.getNoticeCode());
            pstmt.setString(2,notice.getTitle());
            pstmt.setString(3,notice.getContent());
            pstmt.setInt(4,notice.getNoticeId());
            pstmt.setString(5,notice.getWriter());

            rowCnt = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }
}
