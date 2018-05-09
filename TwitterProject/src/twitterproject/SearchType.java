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

    public SearchType() throws TwitterException, IOException {
        // add something eiei
    }

    public void setSearchType(int type) throws TwitterException {
        try {
            switch (type) {
                case 1:
                    FileSearch x = new FileSearch();
                    break;
                case 2:
                    APISearch y = new APISearch();
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
                    startsearch();
            }
        } catch (IOException e) {
            System.out.println("Error IOException");
            System.out.println("-------------------------------------");
            System.exit(-1);
        }
    }

    public void startsearch() throws TwitterException {
        data = new ArrayList<>(); //clear output
        System.out.println("-------------------------------------");
        System.out.println("Twitter Search");
        System.out.println("Choose 1 for Existing file search");
        System.out.println("Choose 2 for API search");
        System.out.println("Choose 3 for Exit program");
        System.out.println("-------------------------------------");
        System.out.print("Choose one : ");
        try {
            int type = SC.nextInt();
            setSearchType(type);
        } catch (InputMismatchException e) {
            System.out.println("Mismatch Input , Try again.");
            System.out.println("-------------------------------------");
            System.out.print("Invalid Input : " + SC.nextLine()); //clear input from nextInt();
            System.out.println();
            startsearch();
        }
    }

    public void continuesearch() throws TwitterException, IOException {
        System.out.println("-------------------------------------");
        System.out.println("Continue Searching? Y/N");
        System.out.println("-------------------------------------");
        System.out.print("Input : ");
        checker = SC.next().toUpperCase();
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
