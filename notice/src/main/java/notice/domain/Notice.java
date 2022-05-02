package notice.domain;

import java.util.Date;

public class Notice {
    private Integer noticeId;
    private Integer noticeCode;
    private String title;
    private String content;
    private String writer;
    private Integer cmt_cnt;
    private Date reg_date;
    private Date up_date;

    public void setNoticeCode(Integer noticeCode) {
        this.noticeCode = noticeCode;
    }

    public Integer getCmt_cnt() {
        return cmt_cnt;
    }

    public void setCmt_cnt(Integer cmt_cnt) {
        this.cmt_cnt = cmt_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

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

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
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
    public Notice(Integer noticeCode,String title, String content, String writer, Date reg_date,Date up_date) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }

    // select 전용
    public Notice(Integer noticeId,int noticeCode, String title, String content, String writer, Integer cmt_cnt, Date reg_date,Date up_date) {
        this.noticeId = noticeId;
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.cmt_cnt = cmt_cnt;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeCode=" + noticeCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", cmt_cnt=" + cmt_cnt +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}
