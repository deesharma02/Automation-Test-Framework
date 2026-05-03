package utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
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
        FileReader fileReader = null;
        String[] data;
        List<User> userList;
        User user;
        try {
            fileReader = new FileReader(csvfile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        assert fileReader != null;
        CSVReader csvReader = new CSVReader(fileReader);
        try {
            csvReader.readNext();
            userList = new ArrayList<>();
            while ((data = csvReader.readNext())!=null){
                user = new User(data[0] , data[1]);
                userList.add(user);
            }
//            for(User userData:userList){
//                System.out.println(userData);
//            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        return userList.iterator();
    }
}
