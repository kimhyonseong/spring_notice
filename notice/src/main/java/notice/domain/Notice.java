package notice.domain;

import java.util.Date;

public class Notice {
    private int noticeId;
    private int noticeCode;
    private String title;
    private String content;
    private String writer;
    private Date reg_date;
    private Date up_date;

    public Date getUpDate() {
        return up_date;
    }

    public void setUpDate(Date up_date) {
        this.up_date = up_date;
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
        return reg_date;
    }

    public void setRegDate(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Notice() {}

    // insert 전용
    public Notice(int noticeCode,String title, String content, String writer, Date reg_date,Date up_date) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }

    // select 전용
    public Notice(int noticeId,int noticeCode, String title, String content, String writer, Date reg_date,Date up_date) {
        this.noticeId = noticeId;
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }
}
