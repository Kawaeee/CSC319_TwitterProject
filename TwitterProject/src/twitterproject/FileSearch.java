package twitterproject;

//import java.util.Scanner;
import java.io.FileNotFoundException;
import twitter4j.TwitterException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FileSearch extends SearchType {

    private String line;
    //private Scanner file;
    private int count;
    CSVParser csvParser;
    String name;
    String text;
    String date;

    public FileSearch() throws TwitterException, IOException {
        tweetparameter = 3;
        System.out.println("-------------------------------------");
        System.out.println("Existing file search");
        System.out.println("-------------------------------------");
        System.out.print(SC.nextLine()); //clear input
        System.out.print("Input your file path : ");
        openFile(SC.nextLine());
        System.out.println("-------------------------------------");
        System.out.print("Input your keyword : ");
        search(SC.nextLine());
        super.continuesearch();
    }

    public boolean openFile(String filename) throws IOException {
        System.out.println("-------------------------------------");
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            System.out.println("Success," + filename + " has been opened.");
            return true;
        } catch (FileNotFoundException catcher) {
            System.out.println("Error," + filename + " not Found.");
            System.exit(-1);
            return false;
        }
    }
    //id screen_name text_tweet location device created_at

    public void search(String keyword) throws IOException {
        word = keyword;
        for (CSVRecord csvRecord : csvParser) {
            name = csvRecord.get("screen_name");
            text = csvRecord.get("text_tweet");
            date = csvRecord.get("created_at");
            if (name.contains(keyword) || text.contains(keyword)) {
                data.add(new Tweet(name, date, text));
            }
            // Accessing Values by Column Index
        }
        super.printResult();

    }

    @Override
    public void printResult() {
        super.printResult();
    }
}
