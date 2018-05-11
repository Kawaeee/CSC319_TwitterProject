package twitterproject;

public class Tweet {

    private String username;
    private String date;
    private String text;
    private String url;

    //*Getter-Setter Methods
    public Tweet(String username, String date, String text) { //file search
        this.username = username;
        this.date = date;
        this.text = text;
    }

    public Tweet(String username, String date, String text, String url) { //api search
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
