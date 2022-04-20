package Controller;

public interface NoticeDao {
    Notice list();

    Notice list(int page, int limit);

    Notice selectNotice(int noticeId);

    int deleteNotice(int noticeId);

    int deleteAllNotice();

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);
}
