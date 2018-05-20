package twitterproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import twitter4j.TwitterException;

public class SearchType {

    final public static Scanner SC = new Scanner(System.in);
    public ArrayList<Tweet> data = new ArrayList<>();
    public String word;
    private String checker;
    public int tweetparameter;
    private FileSearch x;
    private APISearch y;
    private int type;

    public void setSearchType(int type) throws TwitterException {
        try {
            switch (type) {
                case 1:
                    x = new FileSearch();
                    break;
                case 2:
                    y = new APISearch();
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("Thank for using Twitter Search");
                    System.out.println("-------------------------------------");
                    System.exit(-1);
                    break;
                default:
                    System.out.println("-------------------------------------");
                    System.out.println("Mismatch Input , Try again.");
                    startSearch();
            }
        } catch (IOException e) {
            System.out.println("Error IOException");
            System.out.println("-------------------------------------");
            System.exit(-1);
        }
    }

    public void startSearch() throws TwitterException {
        data = new ArrayList<>(); //clear output
        System.out.println("-------------------------------------");
        System.out.println("Twitter Search");
        System.out.println("Choose 1 for Existing file search");
        System.out.println("Choose 2 for API search");
        System.out.println("Choose 3 for Exit program");
        System.out.println("-------------------------------------");
        System.out.print("Choose one : ");
        try {
            type = SC.nextInt();
            setSearchType(type);
        } catch (InputMismatchException e) {
            System.out.println("Mismatch Input , Try again.");
            System.out.println("-------------------------------------");
            System.out.print("Invalid Input : " + SC.nextLine()); //clear input from nextInt();
            System.out.println();
            startSearch();
        }
    }

    public void continuesearch() throws TwitterException, IOException {
        System.out.println("-------------------------------------");
        System.out.println("Continue Searching? Y/N");
        System.out.println("-------------------------------------");
        System.out.print("Input : ");
        checker = SC.next();
        if (checker.equalsIgnoreCase("Y")) {
            startSearch();
        } else {
            System.out.println("-------------------------------------");
            System.out.println("Thank for using Twitter Search");
            System.out.println("-------------------------------------");
            System.exit(-1);
        }
    }

    public void printResult() {
        if (tweetparameter == 3) {
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i).getUsername() + " --- " + data.get(i).getText() + " --- " + data.get(i).getDate());
                System.out.println("");
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i).getUsername() + " --- " + data.get(i).getText() + " --- " + data.get(i).getDate() + " --- " + data.get(i).getUrl());
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("Keyword : " + word);
        System.out.println("Amount : " + data.size());
    }

}
