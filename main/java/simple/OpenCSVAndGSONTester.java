package simple;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OpenCSVAndGSONTester {
    private static final String SAMPLE_CSV_FILE_PATH = "users.csv";
    private static final String SAMPLE_JSON_FILE_PATH = "users.json";

    public static void main(String[] args) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CsvToBeanBuilder<CSVUser> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVUser.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVUser> csvToBean = csvToBeanBuilder.build();
            List<CSVUser> csvUsers = csvToBean.parse();
            Gson gson = new Gson();
            String json = gson.toJson(csvUsers);
            FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
            writer.write(json);
            writer.close();
            BufferedReader br = new BufferedReader(new FileReader(SAMPLE_JSON_FILE_PATH));
            CSVUser[] usrObj = gson.fromJson(br,CSVUser[].class);
            List<CSVUser> csvUserList = Arrays.asList(usrObj);
            Iterator<CSVUser> csvUserIterator = csvUserList.iterator();
            while (csvUserIterator.hasNext()){
                CSVUser csvUser = csvUserIterator.next();
                System.out.println("Name: "+csvUser.getName());
                System.out.println("Email: "+csvUser.getEmail());
                System.out.println("PhoneNo: "+csvUser.getPhoneNo());
                System.out.println("Country: "+csvUser.getCountry());
                System.out.println("=============================");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
