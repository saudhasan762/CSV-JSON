package simple;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {
    public static final String OBJECT_LIST_SAMPLE = "users.csv";

    public static void main(String[] args) throws IOException,
            CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {

        try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST_SAMPLE));
        ){
            StatefulBeanToCsv<CSVUser> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            List<CSVUser> csvUsers = new ArrayList<>();
            csvUsers.add(new CSVUser("Sunder Pichai","sunder.pichai@gmail.com","+1-11112456","India"));
            csvUsers.add(new CSVUser("Satya Nadella","satyan.nadella@gamil.com","+1-11111223","India"));
            beanToCsv.write(csvUsers);
        }
    }
}
