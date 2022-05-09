package notice.service;

import notice.dao.CommentDao;
import notice.dao.NoticeDao;
import notice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    //@Autowired
    NoticeDao noticeDao;
    //@Autowired
    CommentDao commentDao;

    //@Override
    public CommentServiceImpl(NoticeDao notice, CommentDao comment) {
        this.noticeDao = notice;
        this.commentDao = comment;
    }

    @Override
    public List<Comment> view(Integer bno){
        return commentDao.commentList(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(Comment comment) throws Exception{
        //댓글 쓰기와 게시글 댓글 개수 올리기
        noticeDao.updateCommentCnt(comment.getBno(),1);
        return commentDao.write(comment);
//        throw new Exception("comment errors");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Comment comment) throws Exception{
        //댓글 삭제와 게시글 개수 줄이기
//        throw new Exception("comment errors");
        noticeDao.updateCommentCnt(comment.getBno(),-1);
        return commentDao.delete(comment);
    }

    @Override
    public int update(Comment comment) throws Exception{
        return commentDao.update(comment);
    }

    @Override
    public int getCnt(Integer bno) throws Exception{
        return commentDao.getCnt(bno);
    }
}
