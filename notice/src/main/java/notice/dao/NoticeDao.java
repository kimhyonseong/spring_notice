package notice.dao;

import notice.domain.Notice;
import notice.domain.SearchCondition;

import java.util.List;

public interface NoticeDao {
    List<Notice> list();

    List<Notice> list(int page, int limit, int noticeCode);

    Notice selectNotice(int noticeId);

    int deleteNotice(int noticeId, String writer);

    int deleteAllNotice();

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);

    int getCnt();
    int getCnt(Integer noticeCode);

    List<Notice> searchNotice(SearchCondition sc);

    int searchNoticeCnt(SearchCondition sc);

    int updateCommentCnt(int noticeId, int cnt);
}
