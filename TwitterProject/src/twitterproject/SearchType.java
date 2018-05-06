package twitterproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import twitter4j.TwitterException;

public class SearchType {

    Scanner sc = new Scanner(System.in);
    public ArrayList<Tweet> data = new ArrayList<Tweet>();
    public String word;
    String filename;

    public SearchType() {
        // add something
    }

    public void setSearchType(int type) throws TwitterException, IOException {
        if (type == 1) {
            System.out.println("-------------------------------------");
            System.out.println("Existing file search");
            System.out.println("-------------------------------------");
            FileSearch x = new FileSearch();

        } else if (type == 2) {
            System.out.println("-------------------------------------");
            System.out.println("API search");
            System.out.println("-------------------------------------");
            APISearch x = new APISearch();
        } else {
            System.out.println("Invalid Input");
        }
    }

    public void work() throws TwitterException, IOException {
        System.out.println("-------------------------------------");
        System.out.println("Twitter Search");
        System.out.println("Choose 1 for Existing file search");
        System.out.println("Choose 2 for API search");
        System.out.println("-------------------------------------");
        System.out.print("Choose one : ");
        setSearchType(sc.nextInt());
    }

    public void printResult() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).getUsername() + " --- " + data.get(i).getText() + " --- " + data.get(i).getDate() + " --- " + data.get(i).getUrl());
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Keyword : " + word);
        System.out.println("Amount : " + data.size());
    }

}
