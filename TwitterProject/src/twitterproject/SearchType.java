package twitterproject;

import java.util.Scanner;

public class SearchType {

    Scanner sc = new Scanner(System.in);

    public SearchType() {
        work();
    }

    public void setSearchType(int type) {
        if (type == 1) {
            System.out.println("Existing file search");
        } else if (type == 2) {
            System.out.println("API search");
        } else {
            System.out.println("Invalid Input");
        }
    }

    public void work() {
        System.out.println("-------------------------------------");
        System.out.println("Twitter Search");
        System.out.println("Choose 1 for Existing file search");
        System.out.println("Choose 2 for API search");
        System.out.println("-------------------------------------");
        setSearchType(sc.nextInt());
    }

    public void printResult() {
       
    }

}
