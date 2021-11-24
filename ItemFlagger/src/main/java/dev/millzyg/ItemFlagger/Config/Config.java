package dev.millzyg.ItemFlagger.Config;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static dev.millzyg.ItemFlagger.ItemFlagger.instance;

public class Config {
    private String format;

    private boolean printToConsole;
    private boolean logToFile;
    private boolean alertOperators;

    private String[] blocks;
    private String[] items;

    public String getFormat() { return format; }

    public boolean getPrintToConsole() { return printToConsole; }
    public boolean getLogToFile() { return logToFile; }
    public boolean getAlertOperators() { return alertOperators; }

    public String[] getBlocks() { return blocks; }
    public String[] getItems() { return items; }

    public boolean Save() throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(this);

            File configFile = new File(instance.getDataFolder(), "ItemFlagger.json");
            FileWriter writer = new FileWriter(configFile);
            writer.write(json);
            writer.close();

            return true; // wrote to file successfully
        } catch (IOException e) {
            return false; // failed to write to file
        }
    }
}
