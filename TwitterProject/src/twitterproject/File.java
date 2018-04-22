package twitterproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class File {

    private String name;
    private ArrayList<String> data;
    private String line;
    private int count;
    private Scanner sc;
    private File file;

    public boolean openFile(String filename) {
        try {
            sc = new Scanner(new FileReader(filename));
            System.out.println("Success," + filename + " has been opened.");
            return true;
        } catch (FileNotFoundException catcher) {
            System.out.println("Error," + filename + " not Found.");
            return false;
        }
    }

    public void findKeyword(String keyword) {
             
    }

    public void addTweet(Tweet tweet) {

    }
}
