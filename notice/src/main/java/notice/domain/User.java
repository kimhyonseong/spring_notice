package notice.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class User {
    private String id;
    private String pw;
    private String name;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birth;

    public User() {}
    public User(String id, String pw, String name, Date birth) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(pw, user.pw) && Objects.equals(name, user.name) && Objects.equals(birth, user.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw, name, birth);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
