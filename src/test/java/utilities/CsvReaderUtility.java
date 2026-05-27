package utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import pojo.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvReaderUtility {
    public static Iterator<User> readCSVFile(String fileName) {
        String path = System.getProperty("user.dir")
                + File.separator + "testData"
                + File.separator + "loginData.csv";
        File csvfile = new File(path);
        String[] data;
        List<User> userList = new ArrayList<>();
        User user;

        try (CSVReader csvReader = new CSVReader(new FileReader(csvfile))) {
            csvReader.readNext();
            while ((data = csvReader.readNext())!=null){
                user = new User(data[0] , data[1]);
                userList.add(user);
            }
//            for(User userData:userList){
//                System.out.println(userData);
//            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Unable to read CSV test data from " + csvfile.getAbsolutePath(), e);
        }

        return userList.iterator();
    }
}
