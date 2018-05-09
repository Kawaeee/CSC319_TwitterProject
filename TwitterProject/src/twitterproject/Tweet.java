package twitterproject;

import java.util.Date;

public class Tweet {

    private String username;
    private Date date;
    private String text;
    private String url;

    //*Getter-Setter Methods
    public Tweet(String username, Date date, String text, String url) {
        this.username = username;
        this.date = date;
        this.text = text;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
