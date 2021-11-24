package dev.millzyg.ItemFlagger.Config;

import com.google.gson.Gson;


import java.io.*;

public class ConfigReader {
    public static String readFileFromResources(File file) {
        StringBuilder fileContent = new StringBuilder();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent.toString();
    }

    public static Config readItemsFromResource(File file) {
        String json = readFileFromResources(file);
        return new Gson().fromJson(json, Config.class);
    }
}

