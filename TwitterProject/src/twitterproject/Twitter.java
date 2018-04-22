package twitterproject;

import java.util.Scanner;
import twitter4j.TwitterException;


public class Twitter {

    public static void main(String[] args) throws TwitterException {
        Scanner sc = new Scanner(System.in);
        File file = new File();
        Searcher searcher = new Searcher();
        searcher.search(sc.next());
        searcher.printResult();
    }
}
