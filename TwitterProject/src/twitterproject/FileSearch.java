package twitterproject;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import twitter4j.TwitterException;
//import java.util.Date;

public class FileSearch extends SearchType {

    private String line;
    private Scanner file;
    //private Date date;
    private int count;

    public FileSearch() throws TwitterException, IOException {
        System.out.println("-------------------------------------");
        System.out.println("Existing file search");
        System.out.println("-------------------------------------");
        System.out.print(sc.nextLine()); //clear input
        System.out.print("Input your file path : ");
        openFile(sc.nextLine());
        System.out.println("-------------------------------------");
        System.out.print("Input your keyword : ");
        search(sc.nextLine());
        super.continuesearch();
    }

    public boolean openFile(String filename) {
        System.out.println("-------------------------------------");
        try {
            file = new Scanner(new FileReader(filename));
            System.out.println("Success," + filename + " has been opened.");
            return true;
        } catch (FileNotFoundException catcher) {
            System.out.println("Error," + filename + " not Found.");
            catcher.printStackTrace();
            System.exit(-1);
            return false;
        }
    }

    public void search(String keyword) {
        while (file.hasNext()) {
            line = file.nextLine().toLowerCase();
            if (line.contains(keyword)) {
                //data.add(new Tweet("username", date, "text", "url"));
                System.out.println(line);
                count++;
            }
        }
        //System.out.println("Total tweets about " + keyword + " is " + data.size());
        System.out.println("Total tweets about " + keyword + " is " + count);
    }

    @Override
    public void printResult() {
        super.printResult();
    }
}