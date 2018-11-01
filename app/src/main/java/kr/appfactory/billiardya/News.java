package kr.appfactory.billiardya;

public class News {

    String subject;
    String name;
    String date;

    public News(String subject, String name, String date) {
        this.subject = subject;
        this.name = name;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
