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
        String path = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "config.json";
        Gson gson = new Gson();
        File jsonfile = new File(path);
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
