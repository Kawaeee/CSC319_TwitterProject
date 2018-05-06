package twitterproject;

//import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
//import java.util.Date;

public class FileSearch extends SearchType {

    //ArrayList<Tweet> data = new ArrayList<Tweet>();
    private String line;
    private Scanner sc;
    private Scanner file;
    //private Date date;
    private int count;

    public FileSearch() {
        sc = new Scanner(System.in);
        System.out.print("Input your file path : ");
        openFile(sc.nextLine());
        System.out.println("-------------------------------------");
        System.out.print("Input your keyword : ");
        search(sc.nextLine());

    }

    public boolean openFile(String filename) {
        System.out.println("-------------------------------------");
        try {
            file = new Scanner(new FileReader(filename));
            System.out.println("Success," + filename + " has been opened.");
            return true;
        } catch (FileNotFoundException catcher) {
            System.out.println("Error," + filename + " not Found.");
            //catcher.printStackTrace();
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
