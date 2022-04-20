package Controller;

import java.util.Date;

public class Notice {
    private int noticeId;
    private int noticeCode;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date upDate;

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public int getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(int noticeCode) {
        this.noticeCode = noticeCode;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Notice() {}

    // insert 전용
    public Notice(int noticeCode,String title, String content, String writer, Date regDate,Date upDate) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.upDate = upDate;
    }

    // select 전용
    public Notice(int noticeId,int noticeCode, String title, String content, String writer, Date regDate,Date upDate) {
        this.noticeId = noticeId;
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.upDate = upDate;
    }
}
