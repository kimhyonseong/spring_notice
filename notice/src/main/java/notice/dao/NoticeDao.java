package notice.dao;

import notice.domain.Notice;

import java.util.List;

public interface NoticeDao {
    List<Notice> list();

    List<Notice> list(int page, int limit);

    Notice selectNotice(int noticeId);

    int deleteNotice(int noticeId, String writer);

    int deleteAllNotice();

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);

    int getCnt();
}
