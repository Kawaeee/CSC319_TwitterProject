package twitterproject;

//import java.util.Scanner;
import java.io.FileNotFoundException;
import twitter4j.TwitterException;
//import java.io.FileReader;
//import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class FileSearch extends SearchType {

    private String line;
    //private Scanner file;
    //private Date date;
    private int count;


    public FileSearch() throws TwitterException, IOException{
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

    public boolean openFile(String filepath) throws TwitterException, IOException{
        Reader reader = Files.newBufferedReader(Paths.get(filepath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        return false;

    }

    public void search(String keyword) {
        

    }

    @Override
    public void printResult() {
        super.printResult();
    }
}
