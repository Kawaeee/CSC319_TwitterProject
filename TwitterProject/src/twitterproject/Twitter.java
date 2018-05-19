package twitterproject;

import java.io.IOException;
import twitter4j.TwitterException;

public class Twitter {

    public static void main(String[] args) throws TwitterException, IOException {
        SearchType searcher = new SearchType();
        searcher.startSearch();
    }
}
