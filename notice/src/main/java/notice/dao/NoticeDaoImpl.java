package notice.dao;

import notice.domain.Notice;
import notice.domain.SearchCondition;
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
        return list(1,10,1);
    }

    @Override
    public List<Notice> list(int page, int limit,int noticeCode) {
        List<Notice> list = new ArrayList<Notice>();
        String sql = "select notice_no,notice_code,title,content,user_id,reg_date,up_date " +
                "from notice where notice_code = ? order by notice_no desc limit ?,?";
        ResultSet rs = null;
        System.out.println(sql);
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setInt(1, noticeCode);
            pstmt.setInt(2, (page-1) * limit);
            pstmt.setInt(3, limit);
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
        String sql = "select notice_no,notice_code,title,content,user_id,cmt_cnt,reg_date,up_date " +
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
                notice.setCmt_cnt(rs.getInt(6));
                notice.setRegDate(new Date(rs.getDate(7).getTime()));
                notice.setUpDate(new Date(rs.getDate(8).getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notice;
    }

    @Override
    public int getCnt(Integer noticeCode){
        ResultSet rs;
        String sql = "select count(*) as cnt from notice ";

        if (noticeCode != null) {
            sql += "where notice_code = ?";
        }

        int totalCnt = 0;
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            if (noticeCode != null) {
                pstmt.setInt(1, noticeCode);
            }
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
    public int getCnt(){
        return getCnt(null);
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

    @Override
    public List<Notice> searchNotice(SearchCondition sc) {
        String sql = "select notice_no, notice_code, title, content, user_id, reg_date,up_date from notice" +
                " where true and ";
        ResultSet rs = null;
        Notice notice = null;
        List<Notice> list = new ArrayList<>();
        if (sc.getOption() > 2) sc.setOption(2);
        if (sc.getOption() < 0) sc.setOption(0);

        //글 종류
        if (sc.getNoticeCode() != 0) {
            sql += "notice_code = ? and ";
        } else {
            sql += "notice_code in (?,1,2,3) and ";
        }

        switch (sc.getOption()) {
            case 0 :
                sql += "title like concat('%',?,'%') ";
                break;
            case 1 :
                sql += "content like concat('%',?,'%') ";
                break;
            default:
                sql += "(title like concat('%',?,'%') or ";
                sql += "content like concat('%',?,'%')) ";
        }

        sql += "order by notice_no desc limit ?,?";

        try(
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ) {
            pstmt.setInt(1,sc.getNoticeCode());

            // 옵션에 따른 키워드
            if (sc.getOption() == 2) {
                pstmt.setString(2,sc.getKeyword());
                pstmt.setString(3,sc.getKeyword());
                pstmt.setInt(4,sc.getOffset());
                pstmt.setInt(5,sc.getPageSize());
            } else {
                pstmt.setString(2,sc.getKeyword());
                pstmt.setInt(3,sc.getOffset());
                pstmt.setInt(4,sc.getPageSize());
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                notice = new Notice();
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
    public int searchNoticeCnt(SearchCondition sc) {
        String sql = "select count(*) from notice where true and ";
        int rowCnt = 0;
        ResultSet rs;

        //글 종류
        if (sc.getNoticeCode() != 0) {
            sql += "notice_code = ? and ";
        } else {
            sql += "notice_code in (?,1,2,3) and ";
        }

        switch (sc.getOption()) {
            case 0 :
                sql += "title like concat('%',?,'%') ";
                break;
            case 1 :
                sql += "content like concat('%',?,'%') ";
                break;
            default:
                sql += "(title like concat('%',?,'%') or ";
                sql += "content like concat('%',?,'%')) ";
        }

        try(
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1,sc.getNoticeCode());

            // 옵션에 따른 키워드드
            if (sc.getOption() != 2) {
                pstmt.setString(2,sc.getKeyword());
            } else {
                pstmt.setString(2,sc.getKeyword());
                pstmt.setString(3,sc.getKeyword());
            }

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
    public int updateCommentCnt(int noticeId,int cnt) {
        int rowCnt = 0;
        String sql = "update notice set cmt_cnt = cmt_cnt + ? where notice_no = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setInt(1,cnt);
            pstmt.setInt(2,noticeId);
            rowCnt = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int commentCnt(int bno) {
        String sql = "select cmt_cnt from notice where notice_no = ?";
        ResultSet rs;
        int comment_cnt = 0;

        try(
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1,bno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                comment_cnt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment_cnt;
    }
}
