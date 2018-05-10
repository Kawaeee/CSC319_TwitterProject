package twitterproject;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import twitter4j.TwitterException;

public class Twitter {

    public static void main(String[] args) throws TwitterException, IOException, InvalidFormatException {
        SearchType searcher = new SearchType();
        searcher.startsearch();
    }
}
