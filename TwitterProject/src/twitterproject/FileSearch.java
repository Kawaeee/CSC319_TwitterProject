package twitterproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileSearch extends SearchType {

    ArrayList<Tweet> data = new ArrayList<Tweet>();
    private String line;
    private Scanner sc;

    public boolean openFile(String filename) {
        try {
            sc = new Scanner(new FileReader(filename));
            System.out.println("Success," + filename + " has been opened.");
            return true;
        } catch (FileNotFoundException catcher) {
            System.out.println("Error," + filename + " not Found.");
            catcher.printStackTrace();
            return false;
        }
    }

    public void search(String keyword) {
        while (sc.hasNext()) {
            line = sc.nextLine().toLowerCase().toString();
            if (line.contains(keyword)) {
                //data.add();
                System.out.println(line);
            }
            System.out.println("Total tweets about " + keyword + " is " + data.size());

        }

    }
}
