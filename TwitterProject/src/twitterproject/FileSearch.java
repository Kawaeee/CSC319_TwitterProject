package twitterproject;

import java.util.Scanner;
import java.io.FileNotFoundException;
import twitter4j.TwitterException;
//import java.io.FileReader;
//import java.util.Date;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class FileSearch extends SearchType {

    private String line;
    //private Scanner file;
    //private Date date;
    private int count;
    Workbook file;
    Sheet sheet;
    DataFormatter dataFormatter = new DataFormatter();

    public FileSearch() throws TwitterException, IOException, InvalidFormatException {
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

    public boolean openFile(String filepath) throws TwitterException, IOException, InvalidFormatException {
        try {
            System.out.println("-------------------------------------");
            file = WorkbookFactory.create(new File(filepath));
            System.out.println("Sheet has " + file.getNumberOfSheets() + " Sheets : ");
            System.out.println("Success," + filepath + " has been opened.");
            return true;
        } catch (FileNotFoundException x) {
            System.out.println("Error," + filepath + " not Found.");
            super.startsearch();
            return false;
        }

    }

    public void search(String keyword) {
        sheet = file.getSheetAt(0);
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

    }

    @Override
    public void printResult() {
        super.printResult();
    }
}
