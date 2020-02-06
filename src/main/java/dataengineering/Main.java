package dataengineering;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Main {



  public static void main(String[] args) throws IOException, CsvValidationException {

    CsvParser csvParser = new CsvParser("src/Data/testCSV.csv");
    csvParser.printCsv();


    }

  }

