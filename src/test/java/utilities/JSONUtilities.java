package utilities;

import com.google.gson.Gson;
import constants.Environments;
import pojo.Config;
import pojo.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtilities {
    public static Environment readJson(Environments env){
        String path = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "config.json";
        Gson gson = new Gson();
        File jsonfile = new File(path);

        Config config;
        try (FileReader fileReader = new FileReader(jsonfile)) {
            config = gson.fromJson(fileReader , Config.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read environment config from " + jsonfile.getAbsolutePath(), e);
        }

        if (config == null || config.getEnvironment() == null) {
            throw new IllegalStateException("Environment config is empty or invalid: " + jsonfile.getAbsolutePath());
        }

        return config.getEnvironment().get(env.name());
    }
}
