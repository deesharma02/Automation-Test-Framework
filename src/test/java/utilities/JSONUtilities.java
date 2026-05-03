package utilities;

import com.google.gson.Gson;
import constants.Environments;
import pojo.Config;
import pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtilities {
    public static Environment readJson(Environments env){
        Gson gson = new Gson();
        File jsonfile = new File(System.getProperty("user.dir") + "\\config\\config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonfile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        assert fileReader != null;
        Config config = gson.fromJson(fileReader , Config.class);
        return config.getEnvironment().get("QA");
    }
}
