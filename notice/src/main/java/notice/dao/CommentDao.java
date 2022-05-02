package notice.dao;

import notice.domain.Comment;

import java.util.List;

public interface CommentDao {
    int write(Comment comment);

    List<Comment> commentList(Integer bno);

    int update(Comment comment);

    int deleteAll();

    int delete(Comment comment);

    int getCnt(Integer bno);

    int getAllCnt();
}
