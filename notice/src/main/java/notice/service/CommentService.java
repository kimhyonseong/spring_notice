package notice.service;

import notice.dao.CommentDao;
import notice.dao.NoticeDao;
import notice.domain.Comment;
import notice.domain.Notice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    List<Comment> view(Integer bno);
    
    @Transactional(rollbackFor = Exception.class)
    int write(Comment comment) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int delete(Comment comment) throws Exception;

    int update(Comment comment) throws Exception;

    int getCnt(Integer bno) throws Exception;
}
