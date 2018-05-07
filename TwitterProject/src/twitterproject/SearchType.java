package twitterproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import twitter4j.TwitterException;

public class SearchType {

    public static Scanner sc = new Scanner(System.in);
    public ArrayList<Tweet> data = new ArrayList<Tweet>();
    public String word;
    String filename;
    String checker;
    FileSearch x;
    APISearch y;

    public SearchType() throws TwitterException, IOException {
        // add something
    }

    public void setSearchType(int type) throws TwitterException, IOException {
        switch (type) {
            case 1:
                x = new FileSearch();
                break;
            case 2:
                y = new APISearch();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    public void startsearch() throws TwitterException, IOException {
        data = new ArrayList<Tweet>(); //clear output
        System.out.println("-------------------------------------");
        System.out.println("Twitter Search");
        System.out.println("Choose 1 for Existing file search");
        System.out.println("Choose 2 for API search");
        System.out.println("-------------------------------------");
        System.out.print("Choose one : ");
        setSearchType(sc.nextInt());
    }

    public void continuesearch() throws TwitterException, IOException {
        System.out.println("-------------------------------------");
        System.out.println("Continue Searching? Y/N");
        checker = sc.next().toUpperCase();
        if (checker.equalsIgnoreCase("Y")) {
            startsearch();
        } else if (checker.equalsIgnoreCase("N")) {
            System.out.println("-------------------------------------");
            System.out.println("Thank for using Twitter Search");
            System.out.println("-------------------------------------");
            System.exit(-1);
        } else {
            System.out.println("-------------------------------------");
            System.out.println("----------Invalid Input!-------------");
            System.out.println("Thank for using Twitter Search");
            System.out.println("-------------------------------------");
            System.exit(-1);
        }
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
