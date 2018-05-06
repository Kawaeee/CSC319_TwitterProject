package twitterproject;

import java.util.Scanner;
import twitter4j.TwitterException;

public class Twitter {

    public static void main(String[] args) throws TwitterException {
        Scanner sc = new Scanner(System.in);
        //File file = new File();
        SearchType searcher = new SearchType();
        searcher.work();
        //FileSearch file = new FileSearch();
        //file.openFile("");
        //file.search("dog");

        //APISearch searcher = new APISearch();
        //searcher.search(sc.nextLine());
        //searcher.printResult();
        //searcher.getRatelimit();
    }
}
