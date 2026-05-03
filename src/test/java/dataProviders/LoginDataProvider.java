package dataProviders;

import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import pojo.Config;
import pojo.Environment;
import pojo.TestData;
import pojo.User;
import utilities.CsvReaderUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider(){
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "\\testData\\logindata.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(testDataFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        assert fileReader != null;
        TestData testData = gson.fromJson(fileReader , TestData.class);
        List<Object[]> dataToReturn = new ArrayList<>();
        for(User user : testData.getData()){
            dataToReturn.add(new Object[] {user});
        }

        return dataToReturn.iterator();
    }

    @DataProvider(name = "LoginCsvTestDataProvider")
    public Iterator<User> loginCsvDataProvider(){
        return CsvReaderUtility.readCSVFile("loginData");
    }
}
