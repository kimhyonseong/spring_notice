package notice.domain;

import java.util.Date;

public class Comment {
    private int cno;
    private int bno;
    private int pcno;
    private String comment;
    private String writer;
    private Date reg_date;
    private Date up_date;

    public Comment() {}
    public Comment(int bno, String comment, String writer) {
        this.bno = bno;
        this.comment = comment;
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cno=" + cno +
                ", bno=" + bno +
                ", pcno=" + pcno +
                ", comment='" + comment + '\'' +
                ", writer='" + writer + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public int getPcno() {
        return pcno;
    }

    public void setPcno(int pcno) {
        this.pcno = pcno;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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
}
